package com.asusoft.chatserver;

import com.asusoft.chatserver.business.friend.FriendService;
import com.asusoft.chatserver.business.member.MemberService;
import com.asusoft.chatserver.entity.friend.dto.FriendCreateDto;
import com.asusoft.chatserver.entity.member.dto.MemberCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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
        private final FriendService friendService;

        @Transactional
        public void init() throws Exception {
            MemberCreateDto memberDto = new MemberCreateDto("Asu", "asukim2020", "1234");
            MemberCreateDto memberDto2 = new MemberCreateDto("Asu2", "asukim20202", "12342");
            Long saveId = memberService.save(memberDto);
            memberService.save(memberDto2);

            FriendCreateDto dto = new FriendCreateDto(saveId, memberDto2.getName());
            friendService.save(dto);
        }
    }

}
