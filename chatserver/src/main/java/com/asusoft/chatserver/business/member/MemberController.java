package com.asusoft.chatserver.business.member;

import com.asusoft.chatserver.entity.member.dto.MemberCreateDto;
import com.asusoft.chatserver.entity.member.dto.LoginDto;
import com.asusoft.chatserver.entity.member.dto.MemberReadDto;
import com.asusoft.chatserver.exceptionhandler.ExceptionMsg;
import com.asusoft.chatserver.exceptionhandler.exception.DuplicateSaveException;
import com.asusoft.chatserver.exceptionhandler.exception.LoginException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("signup")
    public Long signUp(
            @Validated MemberCreateDto dto,
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
    public MemberReadDto login(
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
    @PostMapping("file")
    public MemberReadDto upload(
            @RequestParam Long memberId,
            @RequestParam MultipartFile file
    ) throws IOException {
        return memberService.uploadProfile(memberId, file);
    }

    @GetMapping("file/{memberId}/{fileName:.+}")
    public Resource download(
            @PathVariable Long memberId,
            @PathVariable String fileName
    ) throws MalformedURLException {
        FileUtil fileUtil = new FileUtil();
        return fileUtil.download(memberId, fileName);
    }

    @PostMapping("fcmToken")
    public Long uploadFcmToken(
            @RequestParam Long memberId,
            @RequestParam String fcmToken
    ) {
        return memberService.uploadFcmToken(memberId, fcmToken);
    }
}
