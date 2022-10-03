package com.asusoft.chatserver.entity.chatroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRoomCreateDto {
    String name;
    Long memberId;
    Long friendId;
}
