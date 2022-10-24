package com.asusoft.chatserver.business.chatting;

import com.asusoft.chatserver.entity.chatting.dto.ChattingCreateDto;
import com.asusoft.chatserver.entity.chatting.dto.ChattingReadDto;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting")
public class ChattingController {
    private final ChattingService chattingService;

    @PostMapping("/create")
    public Long create(ChattingCreateDto dto) throws IOException, FirebaseMessagingException {
        return chattingService.save(dto);
    }

    @GetMapping("/list")
    public List<ChattingReadDto> list(Long chatroomId) {
        return chattingService.list(chatroomId);
    }
}
