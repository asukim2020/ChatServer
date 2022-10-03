package com.asusoft.chatserver.business.chatroom;

import com.asusoft.chatserver.business.entry.EntryRepository;
import com.asusoft.chatserver.business.member.MemberRepository;
import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomCreateDto;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomReadDto;
import com.asusoft.chatserver.entity.entry.Entry;
import com.asusoft.chatserver.entity.entry.dto.EntryCreateDto;
import com.asusoft.chatserver.entity.entry.dto.EntryReadDto;
import com.asusoft.chatserver.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final EntryRepository entryRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    public Long save(ChatRoomCreateDto chatRoomDto) {

        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.create(chatRoomDto));

        EntryCreateDto dto =
                new EntryCreateDto(
                        chatRoom.getId(),
                        chatRoomDto.getMemberId(),
                        chatRoomDto.getFriendId()
                );

        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException("save error in member find in EntryService")
        );

        Member friend = memberRepository.findById(dto.getFriendId()).orElseThrow(
                () -> new IllegalArgumentException("save error in member find in EntryService")
        );

        Entry myEntry = Entry.create(member, chatRoom);
        Entry friendEntry = Entry.create(friend, chatRoom);

        entryRepository.save(myEntry);
        entryRepository.save(friendEntry);

        chatRoom.addEntry(myEntry);
        chatRoom.addEntry(friendEntry);

        return chatRoom.getId();
    }


//    public List<EntryReadDto> list(Long memberId) {
//        List<Entry> entryList = entryRepository.findByMemberId(memberId);
//
//        return entryList.stream().map(EntryReadDto::new).collect(Collectors.toList());
//    }

    public List<ChatRoomReadDto> list(Long memberId) {
        List<Entry> entryList = entryRepository.findByMemberId(memberId);
        return entryList.stream().map(ChatRoomReadDto::new).collect(Collectors.toList());
    }

}
