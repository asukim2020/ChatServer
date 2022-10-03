package com.asusoft.chatserver.entity.chatroom;

import com.asusoft.chatserver.auditing.LastModifiedTimeEntity;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomCreateDto;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomReadDto;
import com.asusoft.chatserver.entity.entry.Entry;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    List<Entry> entryList = new ArrayList<>();

    private ChatRoom(ChatRoomCreateDto dto) {
        name = dto.getName();
    }

//    public ChatRoomReadDto getChatRoomDto() {
//        return new ChatRoomReadDto(id, name);
//    }

    public static ChatRoom create(ChatRoomCreateDto dto) {
        return new ChatRoom(dto);
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
        entry.setChatRoom(this);
    }
}
