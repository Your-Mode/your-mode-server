package com.example.yourmode.domain.member.service;

import com.example.yourmode.domain.member.domain.Member;
import com.example.yourmode.domain.member.repository.MemberRepository;
import com.example.yourmode.domain.member.status.MemberErrorStatus;
import com.example.yourmode.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) throws UsernameNotFoundException{
        return memberRepository.findById(id)
                .orElseThrow(() -> new RestApiException(MemberErrorStatus.EMPTY_MEMBER));
    }

    // 회원 저장
    public Member saveEntity(Member member) {
        return memberRepository.save(member);
    }

}
