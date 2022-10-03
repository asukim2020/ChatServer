package com.asusoft.chatserver.entity.chatroom.dto;

import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.entry.Entry;
import com.asusoft.chatserver.entity.entry.dto.EntryReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ChatRoomReadDto {
    Long id;
    String name;
    List<EntryReadDto> entryList;

    public ChatRoomReadDto(Entry entry) {
        ChatRoom chatRoom = entry.getChatRoom();
        List<Entry> entrys = chatRoom.getEntryList();

        id = chatRoom.getId();
        name = chatRoom.getName();
        entryList = entrys.stream().map(EntryReadDto::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ChatRoomReadDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entryList=" + entryList +
                '}';
    }
}
