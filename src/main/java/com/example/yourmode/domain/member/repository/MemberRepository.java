package com.example.yourmode.domain.member.repository;

import com.example.yourmode.domain.member.domain.Member;
import com.example.yourmode.domain.member.domain.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByClientIdAndLoginType(String clientId, LoginType loginType);
}

