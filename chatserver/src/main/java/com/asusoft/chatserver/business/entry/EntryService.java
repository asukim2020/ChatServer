package com.asusoft.chatserver.business.entry;

import com.asusoft.chatserver.business.chatroom.ChatRoomRepository;
import com.asusoft.chatserver.business.member.MemberRepository;
import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.entry.Entry;
import com.asusoft.chatserver.entity.entry.dto.CreateEntryDto;
import com.asusoft.chatserver.entity.entry.dto.ReadEntryDto;
import com.asusoft.chatserver.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    public Long save(CreateEntryDto dto) {

        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException("save error in member find in EntryService")
        );

        ChatRoom chatRoom = chatRoomRepository.findById(dto.getChatRoomId()).orElseThrow(
                () -> new IllegalArgumentException("save error in chat room find in EntryService")
        );

        Entry entry = Entry.create(member, chatRoom);
        entryRepository.save(entry);

        return entry.getId();
    }


    public List<ReadEntryDto> findMember(Long memberId) {
        List<Entry> entryList = entryRepository.findByMemberId(memberId);

        return entryList.stream().map(ReadEntryDto::new).collect(Collectors.toList());
    }

}
