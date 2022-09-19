package com.asusoft.chatserver.entity.friend;

import com.asusoft.chatserver.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {
    @Id
    @GeneratedValue
    @Column(name = "FRIEND_ID")
    Long id;

    @NotNull
    @Column(name = "MEMBER__ID")
    Long memberId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    Member friend;

    public static Friend create(Member my, Member friend) {
        return new Friend(my, friend);
    }

    private Friend(Member my, Member friend) {
        this.memberId = my.getId();
        this.friend = friend;
    }

}
