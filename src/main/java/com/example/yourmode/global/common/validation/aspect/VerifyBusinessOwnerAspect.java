package com.example.yourmode.global.common.validation.aspect;

import com.example.yourmode.domain.business.service.BusinessOwnerService;
import com.example.yourmode.domain.business.status.BusinessErrorStatus;
import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.domain.member.service.MemberService;
import com.example.yourmode.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class VerifyBusinessOwnerAspect {

    private final MemberService memberService; // 현재 로그인된 사용자 정보를 가져오기 위함
    private final BusinessOwnerService businessOwnerService; // Business 소유 여부 검증 서비스

    @Before("@annotation(com.example.yourmode.global.common.validation.annotation.VerifyBusinessOwner)")
    public void verifyOwnership(JoinPoint joinPoint) {
        // 현재 로그인된 사용자 가져오기
        Member currentMember = memberService.getCurrentMember();

        // BusinessId 추출 (첫 번째 메서드 파라미터가 BusinessId라고 가정)
        Object[] args = joinPoint.getArgs();
        Long businessId = (Long) args[0]; // 비즈니스 ID는 첫 번째 인자로 전달

        // Business 소유자 검증
        if (!businessOwnerService.isOwnerOfBusiness(currentMember.getId(), businessId)) {
            log.error("현재 사용자는 비즈니스 {}의 소유자가 아닙니다.", businessId);
            throw new RestApiException(BusinessErrorStatus.UNABLE_TO_HANDLE_BUSINESS);
        }
    }
}