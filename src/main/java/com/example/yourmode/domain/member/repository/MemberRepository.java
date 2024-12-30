package com.example.yourmode.domain.member.repository;

import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.domain.member.entity.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByClientIdAndLoginType(String clientId, LoginType loginType);
    Optional<Member> findByName(String name);
    Optional<Member> findByPhone(String phone);
    Boolean existsByPhone(String phone);
}

