package com.asusoft.chatserver.entity.chatting;

import com.asusoft.chatserver.auditing.LastModifiedTimeEntity;
import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.chatting.dto.ChattingCreateDto;
import com.asusoft.chatserver.entity.chatting.dto.ChattingReadDto;
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
public class Chatting extends LastModifiedTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "CHATTING_ID")
    Long id;

    @NotNull(message = "Chatting message")
    String message;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ROOM_ID")
    ChatRoom chatRoom;

    private Chatting(String message, Member member, ChatRoom chatRoom) {
        this.message = message;
        this.member = member;
        this.chatRoom = chatRoom;
    }

    public static Chatting create(String message, Member member, ChatRoom chatRoom) {
        return new Chatting(message, member, chatRoom);
    }
}
