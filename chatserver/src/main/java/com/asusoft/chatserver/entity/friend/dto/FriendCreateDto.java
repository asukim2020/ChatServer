package com.asusoft.chatserver.entity.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendCreateDto {

    Long myId;
    String friendName;

}
