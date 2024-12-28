package com.example.yourmode.domain.business.mapper;

import com.example.yourmode.domain.business.dto.request.BusinessRequestDto;
import com.example.yourmode.domain.business.dto.response.BusinessInfoResponse;
import com.example.yourmode.domain.business.dto.response.BusinessListResponse;
import com.example.yourmode.domain.business.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BusinessMapper {

    public static Business toBusiness(BusinessRequestDto request) {
        return Business.builder()
                .businessType(request.businessType())
                .name(request.name())
                .address(request.address())
                .siteUrl(request.siteUrl())
                .build();
    }

    public static BusinessListResponse toBusinessListResponse(Page<Business> businessPage) {
        return BusinessListResponse.builder()
                .currentPage(businessPage.getNumber()) // 현재 페이지 번호
                .totalPages(businessPage.getTotalPages()) // 전체 페이지 수
                .totalElements(businessPage.getTotalElements()) // 전체 항목 수
                .businessList(businessPage.getContent().stream()
                        .map(business -> BusinessListResponse.BusinessResponse.builder()
                                .businessId(business.getId())
                                .ownerId(business.getMember().getId())
                                .businessName(business.getName())
                                .businessType(business.getBusinessType())
                                .build())
                        .collect(Collectors.toList())) // 각 Business 엔티티를 DTO로 변환
                .build();
    }

    public static BusinessInfoResponse toBusinessInfoResponse(Business business) {
        return BusinessInfoResponse.builder()
                .businessId(business.getId())
                .ownerId(business.getMember().getId())
                .businessName(business.getName())
                .businessType(business.getBusinessType())
                .businessAddress(business.getAddress())
                .businessSitUrl(business.getSiteUrl())
                .build();
    }
}
