package com.example.yourmode.domain.business.repository;

import com.example.yourmode.domain.business.entity.Business;
import com.example.yourmode.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findByMember(Member member);
}
