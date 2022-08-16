package com.asusoft.chatserver.entity.chatroom;

import com.asusoft.chatserver.auditing.LastModifiedTimeEntity;
import com.asusoft.chatserver.entity.chatroom.dto.CreateChatRoomDto;
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
public class ChatRoom extends LastModifiedTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "CHAT_ROOM_ID")
    Long id;

    @NotNull(message = "ChatRoom name")
    @Column(unique = true)
    String name;

    private ChatRoom(CreateChatRoomDto dto) {
        name = dto.getName();
    }

    public static ChatRoom create(CreateChatRoomDto dto) {
        return new ChatRoom(dto);
    }
}
