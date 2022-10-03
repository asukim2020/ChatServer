package com.asusoft.chatserver;

import com.asusoft.chatserver.business.chatroom.ChatRoomService;
import com.asusoft.chatserver.business.friend.FriendService;
import com.asusoft.chatserver.business.member.MemberRepository;
import com.asusoft.chatserver.business.member.MemberService;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomCreateDto;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomReadDto;
import com.asusoft.chatserver.entity.friend.dto.FriendCreateDto;
import com.asusoft.chatserver.entity.member.Member;
import com.asusoft.chatserver.entity.member.dto.MemberCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class initData {

    private final InitDataService initDataService;

    @PostConstruct
    public void init() throws Exception {
        initDataService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitDataService {
        private final MemberService memberService;
        private final MemberRepository memberRepository;
        private final FriendService friendService;

        private final ChatRoomService chatRoomService;

        @Transactional
        public void init() throws Exception {
            MemberCreateDto memberDto = new MemberCreateDto("Asu", "asukim2020", "1234");
            MemberCreateDto memberDto2 = new MemberCreateDto("Asu2", "asukim20202", "12342");
            Long saveId = memberService.save(memberDto);
            Long saveId2 = memberService.save(memberDto2);

            Optional<Member> member = memberRepository.findById(saveId);
            member.get().updateProfileUrl("http://172.30.1.10:8080/member/file/1/1.jpg");

            FriendCreateDto dto = new FriendCreateDto(saveId, memberDto2.getName());
            friendService.save(dto);

            ChatRoomCreateDto chatRoomDto = new ChatRoomCreateDto("채팅방", saveId, saveId2);
            chatRoomService.save(chatRoomDto);

            List<ChatRoomReadDto> list = chatRoomService.list(saveId);
            System.out.println("==========================");
            System.out.println(list);
            System.out.println("==========================");
        }
    }

}
