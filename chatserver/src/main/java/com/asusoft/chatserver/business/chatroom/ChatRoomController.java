package com.asusoft.chatserver.business.chatroom;

import com.asusoft.chatserver.business.entry.EntryService;
import com.asusoft.chatserver.entity.chatroom.ChatRoom;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomCreateDto;
import com.asusoft.chatserver.entity.chatroom.dto.ChatRoomReadDto;
import com.asusoft.chatserver.entity.entry.dto.EntryCreateDto;
import com.asusoft.chatserver.entity.entry.dto.EntryReadDto;
import com.asusoft.chatserver.exceptionhandler.ExceptionMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/create")
    public Long create(
            @Validated ChatRoomCreateDto dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.warn("error={} ", bindingResult);
            throw new IllegalArgumentException(ExceptionMsg.bindingMsg(bindingResult));
        }

        return chatRoomService.save(dto);
    }

    @GetMapping("/list")
    public List<ChatRoomReadDto> list(Long memberId) {
        return chatRoomService.list(memberId);
    }
}
