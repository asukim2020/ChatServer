package com.asusoft.chatserver.entity.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {
    String id;
    String pw;
}
