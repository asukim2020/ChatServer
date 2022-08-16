package com.asusoft.chatserver.entity.chatroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadChatRoomDto {
    Long id;
    String name;
}
