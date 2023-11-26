package com.asusoft.chatserver.business.entry;


import com.asusoft.chatserver.entity.entry.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByChatRoomId(Long chatRoomId);

    @Query("select e from Entry e" +
            " join fetch e.member m" +
            " join fetch e.chatRoom cr" +
            " where e.member.key = :id")
    List<Entry> findByMemberId(@Param(value = "id") Long memberId);
}
