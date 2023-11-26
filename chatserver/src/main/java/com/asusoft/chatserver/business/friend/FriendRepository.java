package com.asusoft.chatserver.business.friend;

import com.asusoft.chatserver.entity.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByMemberKey(Long memberKey);

    Optional<Friend> findByMemberKeyAndKey(Long memberKey, Long friendKey);

}
