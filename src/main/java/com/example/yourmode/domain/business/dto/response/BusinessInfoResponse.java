package com.example.yourmode.domain.business.dto.response;

import com.example.yourmode.domain.business.entity.BusinessType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessInfoResponse {
    Long businessId;
    Long ownerId;
    String businessName;
    String businessAddress;
    String businessSitUrl;
    BusinessType businessType;
}
