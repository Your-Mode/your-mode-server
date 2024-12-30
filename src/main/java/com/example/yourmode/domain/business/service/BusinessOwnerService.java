package com.example.yourmode.domain.business.service;


import com.example.yourmode.domain.business.dto.request.BusinessRequestDto;
import com.example.yourmode.domain.business.dto.response.BusinessIdResponseDto;
import com.example.yourmode.domain.business.entity.Business;
import com.example.yourmode.domain.member.entity.Member;

public interface BusinessOwnerService {
    BusinessIdResponseDto createBusiness(Member member, BusinessRequestDto businessRequestDto);

    BusinessIdResponseDto updateBusiness(Long id, BusinessRequestDto businessRequestDto);

    BusinessIdResponseDto deleteBusiness(Long id);

    Boolean isOwnerOfBusiness(Long memberId, Long businessId);

    Business getBusinessByMember(Member member);
}
