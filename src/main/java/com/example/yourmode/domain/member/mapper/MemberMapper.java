package com.example.yourmode.domain.member.mapper;

import com.example.yourmode.domain.member.domain.Member;
import com.example.yourmode.domain.member.domain.LoginType;
import com.example.yourmode.domain.member.domain.Role;
import com.example.yourmode.domain.member.dto.response.MemberLoginResponse;
import com.example.yourmode.global.config.security.jwt.TokenInfo;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toMember(final String clientId, LoginType loginType){
        return Member.builder()
                .clientId(clientId)
                .loginType(loginType)
                .build();
    }

    public MemberLoginResponse toLoginMember(final Member member, TokenInfo tokenInfo, boolean isServiceMember, Role role) {
        return MemberLoginResponse.builder()
                .memberId(member.getId())
                .accessToken(tokenInfo.accessToken())
                .refreshToken(tokenInfo.refreshToken())
                .isServiceMember(isServiceMember)
                .build();
    }
}

