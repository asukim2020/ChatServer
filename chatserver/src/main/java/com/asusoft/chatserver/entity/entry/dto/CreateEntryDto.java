package com.asusoft.chatserver.entity.entry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEntryDto {
    Long chatRoomId;
    Long memberId;
}
