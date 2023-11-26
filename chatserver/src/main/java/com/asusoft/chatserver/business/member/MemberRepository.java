package com.asusoft.chatserver.business.member;

import com.asusoft.chatserver.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(String loginId);

    Optional<Member> findByName(String name);

    Optional<Member> findByProfileUrl(String url);
}
