package com.asusoft.chatserver.entity.member;

import com.asusoft.chatserver.auditing.LastModifiedTimeEntity;
import com.asusoft.chatserver.entity.member.dto.MemberCreateDto;
import com.asusoft.chatserver.entity.member.dto.MemberReadDto;
import com.sun.istack.Nullable;
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

    @Nullable
    String fcmToken;

    @Nullable
    String profileUrl;


    private Member(MemberCreateDto dto) {
        name = dto.getName();
        loginId = dto.getId();
        loginPw = dto.getPw();
    }

    public void updateProfileUrl(String url) {
        this.profileUrl = url;
    }

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public MemberReadDto getReadDto() {
        return new MemberReadDto(id, name, profileUrl);
    }

    public static Member create(MemberCreateDto dto) {
        return new Member(dto);
    }
}
