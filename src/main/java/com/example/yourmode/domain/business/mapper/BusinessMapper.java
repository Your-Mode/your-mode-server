package com.example.yourmode.domain.business.mapper;

import com.example.yourmode.domain.business.dto.request.BusinessRequestDto;
import com.example.yourmode.domain.business.entity.Business;
import org.springframework.stereotype.Component;

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
}
