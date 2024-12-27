package com.example.yourmode.global.config.security;

import com.example.yourmode.domain.member.service.MemberService;
import com.example.yourmode.global.config.security.auth.CustomAccessDeniedHandler;
import com.example.yourmode.global.config.security.jwt.JwtAuthenticationFilter;
import com.example.yourmode.global.config.security.jwt.JwtExceptionFilter;
import com.example.yourmode.global.config.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeanRegister {

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        return new JwtExceptionFilter();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtProvider jwtProvider, MemberService memberService) {
        return new JwtAuthenticationFilter(jwtProvider, memberService);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
