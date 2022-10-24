package com.asusoft.chatserver.entity.chatting.dto;

import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.chatting.Chatting;
import com.asusoft.chatserver.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ChattingCreateDto {
    String message;
    Long memberId;
    Long friendId;
    Long chatRoomId;
}
