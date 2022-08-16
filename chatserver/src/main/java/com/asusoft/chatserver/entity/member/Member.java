package com.asusoft.chatserver.entity.member;

import com.asusoft.chatserver.auditing.LastModifiedTimeEntity;
import com.asusoft.chatserver.entity.member.dto.CreateMemberDto;
import com.asusoft.chatserver.entity.member.dto.ReadMemberDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends LastModifiedTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    Long id;

    @NotNull(message = "Company name")
    @Column(unique = true)
    String name;

    @NotNull(message = "Company loginId")
    @Column(unique = true)
    String loginId;

    @NotNull(message = "Company loginPw")
    String loginPw;

    private Member(CreateMemberDto dto) {
        name = dto.getName();
        loginId = dto.getId();
        loginPw = dto.getPw();
    }

    public ReadMemberDto getReadDto() {
        return new ReadMemberDto(name);
    }

    public static Member create(CreateMemberDto dto) {
        return new Member(dto);
    }
}
