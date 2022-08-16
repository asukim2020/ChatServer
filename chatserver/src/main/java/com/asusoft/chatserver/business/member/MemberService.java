package com.asusoft.chatserver.business.member;

import com.asusoft.chatserver.entity.member.dto.CreateMemberDto;
import com.asusoft.chatserver.entity.member.dto.LoginDto;
import com.asusoft.chatserver.entity.member.Member;
import com.asusoft.chatserver.entity.member.dto.ReadMemberDto;
import com.asusoft.chatserver.exceptionhandler.exception.DuplicateSaveException;
import com.asusoft.chatserver.exceptionhandler.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(CreateMemberDto dto) throws DuplicateSaveException {
        Member member = Member.create(dto);
        try {
            Member saveMember = memberRepository.save(member);
            return saveMember.getId();
        } catch (Exception e) {
            throw new DuplicateSaveException("duplicate sign up exception");
        }
    }

    private Member get(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("get error in MemberService")
        );

        return member;
    }

    private Member get(String loginId) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new IllegalArgumentException("get error in MemberService")
        );

        return member;
    }

    public ReadMemberDto login(LoginDto dto) throws LoginException {
        Member member = get(dto.getId());
        if (member.getLoginPw().equals(dto.getPw())) {
            return member.getReadDto();
        }

        throw new LoginException("login fail");
    }
    
    // TODO : - update 추가
}
