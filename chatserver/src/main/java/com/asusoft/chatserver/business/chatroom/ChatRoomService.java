package com.asusoft.chatserver.business.chatroom;

import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.chatroom.dto.CreateChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Long save(CreateChatRoomDto dto) {
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.create(dto));
        return chatRoom.getId();
    }

}
