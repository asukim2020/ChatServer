package com.asusoft.chatserver.entity.entry;

import com.asusoft.chatserver.auditing.CreateTimeEntity;
import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.entry.dto.CreateEntryDto;
import com.asusoft.chatserver.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Entry extends CreateTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "ENTRY_ID")
    Long id;

    @NotNull(message = "Entry Member")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    Member member;

    @NotNull(message = "Entry ChatRoom")
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_ID")
    ChatRoom chatRoom;

    private Entry(Member member, ChatRoom chatRoom) {
        this.member = member;
        this.chatRoom = chatRoom;
    }

    public static Entry create(Member member, ChatRoom chatRoom) {
        return new Entry(member, chatRoom);
    }
}