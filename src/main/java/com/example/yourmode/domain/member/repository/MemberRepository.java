package com.example.yourmode.domain.member.repository;

import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.domain.member.entity.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByClientIdAndLoginType(String clientId, LoginType loginType);
}

