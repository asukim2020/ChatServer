package com.asusoft.chatserver.entity.entry.dto;

import com.asusoft.chatserver.entity.entry.Entry;
import com.asusoft.chatserver.entity.member.dto.MemberReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryReadDto {
    Long id;
    MemberReadDto member;

    public EntryReadDto(Entry entry) {
        id = entry.getId();
        member = entry.getMember().getReadDto();
    }

    @Override
    public String toString() {
        return "EntryReadDto{" +
                "id=" + id +
                ", member=" + member +
                '}';
    }
}
