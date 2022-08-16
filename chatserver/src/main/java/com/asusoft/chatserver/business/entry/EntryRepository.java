package com.asusoft.chatserver.business.entry;


import com.asusoft.chatserver.entity.entry.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByChatRoomId(Long chatRoomId);

    List<Entry> findByMemberId(Long memberId);

}
