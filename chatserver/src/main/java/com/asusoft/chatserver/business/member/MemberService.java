package com.asusoft.chatserver.business.member;

import com.asusoft.chatserver.entity.member.dto.MemberCreateDto;
import com.asusoft.chatserver.entity.member.dto.LoginDto;
import com.asusoft.chatserver.entity.member.Member;
import com.asusoft.chatserver.entity.member.dto.MemberReadDto;
import com.asusoft.chatserver.exceptionhandler.exception.DuplicateSaveException;
import com.asusoft.chatserver.exceptionhandler.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @PostConstruct
    private void init() {

    }

    public Long save(MemberCreateDto dto) throws DuplicateSaveException {
        Member member = Member.create(dto);
        try {
            Member saveMember = memberRepository.save(member);
            return saveMember.getKey();
        } catch (Exception e) {
            throw new DuplicateSaveException("duplicate sign up exception");
        }
    }

    private Member get(Long id) throws LoginException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new LoginException("get error in MemberService")
        );

        return member;
    }

    private Member get(String loginId) throws LoginException {
        Member member = memberRepository.findById(loginId).orElseThrow(
                () -> new LoginException("get error in MemberService")
        );

        return member;
    }

    public MemberReadDto login(LoginDto dto) throws LoginException {
        Member member = get(dto.getId());
        if (member.getPw().equals(dto.getPw())) {
            return member.getReadDto();
        }

        throw new LoginException("login fail");
    }

    @Transactional
    public MemberReadDto uploadProfile(Long memberId, MultipartFile file) throws IOException {
        FileUtil fileUtil = new FileUtil();
        String url = fileUtil.upload(memberId, file);

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("not found member")
        );

        member.updateProfileUrl(url);

        return member.getReadDto();
    }

    @Transactional
    public Long uploadFcmToken(Long memberId, String fcmToken) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("not found member")
        );

        member.updateFcmToken(fcmToken);
        return member.getKey();
    }

    // TODO : - update 추가
}
