package com.asusoft.chatserver.entity.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberReadDto {
    Long key;
    String id;
    String name;
    String profileUrl;

    @Override
    public String toString() {
        return "MemberReadDto{" +
                "key=" + key +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }
}
