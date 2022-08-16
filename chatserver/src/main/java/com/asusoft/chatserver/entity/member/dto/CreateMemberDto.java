package com.asusoft.chatserver.entity.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateMemberDto {
    String name;
    String id;
    String pw;
}
