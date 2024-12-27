package com.example.yourmode.domain.business.service;

import com.example.yourmode.domain.business.dto.request.BusinessRequestDto;
import com.example.yourmode.domain.business.dto.response.BusinessIdResponseDto;
import com.example.yourmode.domain.business.entity.Business;
import com.example.yourmode.domain.business.mapper.BusinessMapper;
import com.example.yourmode.domain.business.repository.BusinessRepository;
import com.example.yourmode.domain.business.status.BusinessErrorStatus;
import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.domain.member.entity.Role;
import com.example.yourmode.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessOwnerServiceImpl implements BusinessOwnerService{

    private final BusinessRepository businessRepository;

    @Override
    @Transactional
    public BusinessIdResponseDto createBusiness(Member member, BusinessRequestDto businessRequestDto) {

        // 업장 생성
        Business business = BusinessMapper.toBusiness(businessRequestDto);

        // 멤버의 역할이 업주가 아닌 경우
        if(! Role.BUSINESS.equals(member.getRole()))
            throw new RestApiException(BusinessErrorStatus.UNABLE_TO_CREATE_BUSINESS);

        // 업주 등록
        business.setMember(member);

        // DB 저장
        Long id = businessRepository.save(business).getId();

        return new BusinessIdResponseDto(id);
    }

    @Override
    public BusinessIdResponseDto updateBusiness(Long businessId, BusinessRequestDto businessRequestDto) {
        // 업장 찾기
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RestApiException(BusinessErrorStatus.EMPTY_BUSINESS));

        // 업장 정보 수정
        business.updateBusiness(businessRequestDto);

        return new BusinessIdResponseDto(businessId);
    }

    @Override
    public BusinessIdResponseDto deleteBusiness(Long businessId) {
        // 업장 찾기
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RestApiException(BusinessErrorStatus.EMPTY_BUSINESS));

        // 업장 삭제
        businessRepository.delete(business);

        return new BusinessIdResponseDto(businessId);
    }
}
