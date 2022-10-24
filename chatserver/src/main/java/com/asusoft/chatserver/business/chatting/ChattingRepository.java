package com.asusoft.chatserver.business.chatting;

import com.asusoft.chatserver.entity.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingRepository  extends JpaRepository<Chatting, Long> {
    List<Chatting> findByChatRoomId(Long chatroomId);
}
