package com.asusoft.chatserver.business.member;

import com.asusoft.chatserver.entity.member.dto.CreateMemberDto;
import com.asusoft.chatserver.entity.member.dto.LoginDto;
import com.asusoft.chatserver.entity.member.Member;
import com.asusoft.chatserver.entity.member.dto.ReadMemberDto;
import com.asusoft.chatserver.exceptionhandler.ExceptionMsg;
import com.asusoft.chatserver.exceptionhandler.exception.DuplicateSaveException;
import com.asusoft.chatserver.exceptionhandler.exception.LoginException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("signup")
    public Long signUp(
            @Validated CreateMemberDto dto,
            BindingResult bindingResult
    ) throws DuplicateSaveException {
        if (bindingResult.hasErrors()) {
            log.warn("error={} ", bindingResult);
            throw new IllegalArgumentException(ExceptionMsg.bindingMsg(bindingResult));
        }

        return memberService.save(dto);
    }


    // 로그인
    @PostMapping("login")
    public ReadMemberDto login(
            @Validated LoginDto dto,
            BindingResult bindingResult
    ) throws LoginException {
        if (bindingResult.hasErrors()) {
            log.warn("error={} ", bindingResult);
            throw new IllegalArgumentException(ExceptionMsg.bindingMsg(bindingResult));
        }

        return memberService.login(dto);
    }

    // TODO: - 회원 정보 수정 기능 추가
}
