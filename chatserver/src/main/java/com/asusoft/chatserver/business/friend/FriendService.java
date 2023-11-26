package com.asusoft.chatserver.business.friend;

import com.asusoft.chatserver.business.member.MemberRepository;
import com.asusoft.chatserver.entity.friend.Friend;
import com.asusoft.chatserver.entity.friend.dto.FriendCreateDto;
import com.asusoft.chatserver.entity.member.Member;
import com.asusoft.chatserver.entity.member.dto.MemberReadDto;
import com.asusoft.chatserver.exceptionhandler.exception.DuplicateSaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    public Long save(FriendCreateDto dto) throws DuplicateSaveException {

        Member my = memberRepository.findById(dto.getMyId()).orElseThrow(
                () -> new IllegalArgumentException("not found member")
        );

        Member friend = memberRepository.findByName(dto.getFriendName()).orElseThrow(
                () -> new IllegalArgumentException("not found member")
        );

        Optional<Friend> findFriend = friendRepository.findByMemberKeyAndKey(my.getKey(), friend.getKey());
        if (findFriend.isPresent()) {
            throw new DuplicateSaveException("duplicate addFriend exception");
        }

        Friend createFriend = Friend.create(my, friend);

        try {
            Friend saveFriend = friendRepository.save(createFriend);
            return saveFriend.getKey();
        } catch (Exception e) {
            throw new DuplicateSaveException("duplicate addFriend exception");
        }

    }

    public List<MemberReadDto> list(Long memberId) {
        List<Friend> friendList = friendRepository.findByMemberKey(memberId);

        return friendList.stream()
                .map(friend -> friend.getFriend().getReadDto())
                .collect(Collectors.toList());
    }

    public Long remove(Long memberKey, Long friendKey) {
        Friend friend = friendRepository.findByMemberKeyAndKey(memberKey, friendKey).orElseThrow(
                () -> new IllegalArgumentException("not found friend")
        );

        friendRepository.delete(friend);
        return friend.getKey();
    }
}
