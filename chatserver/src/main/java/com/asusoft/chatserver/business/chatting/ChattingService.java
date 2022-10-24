package com.asusoft.chatserver.business.chatting;

import com.asusoft.chatserver.FcmConfig;
import com.asusoft.chatserver.business.chatroom.ChatRoomRepository;
import com.asusoft.chatserver.business.member.MemberRepository;
import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.chatting.Chatting;
import com.asusoft.chatserver.entity.chatting.dto.ChattingCreateDto;
import com.asusoft.chatserver.entity.chatting.dto.ChattingReadDto;
import com.asusoft.chatserver.entity.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final FcmConfig fcmConfig;
    
    public ChattingReadDto save(ChattingCreateDto dto) throws IOException, FirebaseMessagingException {

        String message = dto.getMessage();

        Member my = memberRepository.findById(dto.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException("save error in member find in ChattingService")
        );

        Member friend = memberRepository.findById(dto.getFriendId()).orElseThrow(
                () -> new IllegalArgumentException("save error in member find in ChattingService")
        );

        ChatRoom chatRoom = chatRoomRepository.findById(dto.getChatRoomId()).orElseThrow(
                () -> new IllegalArgumentException("save error in chatroom find in ChattingService")
        );

        Chatting chatting = Chatting.create(message, my, chatRoom);
        chattingRepository.save(chatting);

        ChattingReadDto chattingReadDto = new ChattingReadDto(chatting);
        sendFcm(friend.getFcmToken(), my.getName(), chattingReadDto);

        return new ChattingReadDto(chatting);
    }

    public void sendFcm(String fcmToken, String title, ChattingReadDto dto) throws FirebaseMessagingException, IOException {
        if (fcmToken == null) return;

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        Message message = Message.builder()
                .setToken(fcmToken)
                .putData("title", title)
                .putData("message", dto.getMessage())
                .putData("dto", json)
                .build();

        FirebaseMessaging.getInstance(fcmConfig.firebaseApp()).send(message);
    }

    public void sendFcmTest(String fcmToken) throws FirebaseMessagingException, IOException {
        Message message = Message.builder()
                .setToken(fcmToken)
                .putData("title", "제목")
                .putData("message", "메세지")
                .putData("test", "테스트")
                .build();

        FirebaseMessaging.getInstance(fcmConfig.firebaseApp()).send(message);
    }


    public List<ChattingReadDto> list(Long chatroomId) {
        List<Chatting> list = chattingRepository.findByChatRoomId(chatroomId);
        return list.stream().map(ChattingReadDto::new).collect(Collectors.toList());
    }
}
