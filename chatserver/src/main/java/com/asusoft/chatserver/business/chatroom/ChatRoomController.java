package com.asusoft.chatserver.business.chatroom;

import com.asusoft.chatserver.entity.chatroom.dto.CreateChatRoomDto;
import com.asusoft.chatserver.exceptionhandler.ExceptionMsg;
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
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/create")
    public Long create(
            @Validated CreateChatRoomDto dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.warn("error={} ", bindingResult);
            throw new IllegalArgumentException(ExceptionMsg.bindingMsg(bindingResult));
        }

        return chatRoomService.save(dto);
    }

}
