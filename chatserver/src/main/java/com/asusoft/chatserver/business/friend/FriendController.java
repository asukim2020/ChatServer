package com.asusoft.chatserver.business.friend;

import com.asusoft.chatserver.entity.friend.dto.FriendCreateDto;
import com.asusoft.chatserver.entity.member.dto.MemberReadDto;
import com.asusoft.chatserver.exceptionhandler.exception.DuplicateSaveException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    @PostMapping("addFriend")
    public Long addFriend(FriendCreateDto dto) throws DuplicateSaveException {
        return friendService.save(dto);
    }

    @GetMapping
    public List<MemberReadDto> getFriendList(Long memberId) {
        return friendService.list(memberId);
    }

    @PostMapping("delete")
    public Long removeFriend(Long memberId, Long friendId) {
        return friendService.remove(memberId, friendId);
    }
}
