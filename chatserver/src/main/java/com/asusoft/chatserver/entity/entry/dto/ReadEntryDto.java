package com.asusoft.chatserver.entity.entry.dto;

import com.asusoft.chatserver.entity.entry.Entry;

public class ReadEntryDto {

    Long id;
    Long chatRoomId;
    Long memberId;


    public ReadEntryDto(Entry entry) {
        id = entry.getId();
        chatRoomId = entry.getChatRoom().getId();
        memberId = entry.getMember().getId();
    }

}
