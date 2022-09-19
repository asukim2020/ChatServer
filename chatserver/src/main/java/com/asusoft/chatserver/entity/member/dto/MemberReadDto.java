package com.asusoft.chatserver.entity.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberReadDto {
    Long id;
    String name;
}
