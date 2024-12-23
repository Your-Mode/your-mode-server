package com.example.yourmode.domain.member.strategy;

import com.example.yourmode.domain.member.dto.response.MemberLoginResponse;

public interface LoginStrategy {
    MemberLoginResponse login(String accessToken);
}
