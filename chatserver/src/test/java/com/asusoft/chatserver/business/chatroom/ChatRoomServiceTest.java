package com.asusoft.chatserver.business.chatroom;

import com.asusoft.chatserver.FcmConfig;
import com.asusoft.chatserver.business.chatting.ChattingService;
import com.asusoft.chatserver.business.friend.FriendService;
import com.asusoft.chatserver.business.member.MemberRepository;
import com.asusoft.chatserver.business.member.MemberService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@SpringBootTest
@Transactional
class ChatRoomServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    FriendService friendService;

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    FcmConfig fcmConfig;

    @Autowired
    ChattingService chattingService;

    @Test
    void save() throws IOException, FirebaseMessagingException {
        String token = "fn0BSV6QQA6csvjvdTXzv0:APA91bFO60v1L2GZJx7Qi9Yl6VQbOuDPaZ0MlepxnFsTYieiIzozFqT8G8evsLlSIQlOA0D9LAVwLPLdgkeAORiZi1gH8_MMxgQlyc35t4sEyta53kTIrFA7EJW0kzWVgFRGyMu7FXxN";
        chattingService.sendFcmTest(token);
    }

//    @Test
//    void list() throws Exception {
//        init();
//    }
//
//    @Transactional
//    public void init() throws Exception {
//        MemberCreateDto memberDto = new MemberCreateDto("Asu", "asukim2020", "1234");
//        MemberCreateDto memberDto2 = new MemberCreateDto("Asu2", "asukim20202", "12342");
//        Long saveId = memberService.save(memberDto);
//        Long saveId2 = memberService.save(memberDto2);
//
//        Optional<Member> member = memberRepository.findById(saveId);
//        member.get().updateProfileUrl("http://172.30.1.10:8080/member/file/1/1.jpg");
//
//        FriendCreateDto friendDto = new FriendCreateDto(saveId, memberDto2.getName());
//        friendService.save(friendDto);
//
//        ChatRoomCreateDto chatRoomDto = new ChatRoomCreateDto("채팅방", saveId, saveId2);
//        chatRoomService.save(chatRoomDto);
//
//        List<ChatRoomReadDto> list = chatRoomService.list(saveId);
//        System.out.println("==========================");
//        System.out.println(list);
//        System.out.println("==========================");
//    }
}