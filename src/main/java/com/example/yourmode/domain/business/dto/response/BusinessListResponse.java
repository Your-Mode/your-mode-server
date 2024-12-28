package com.example.yourmode.domain.business.dto.response;

import com.example.yourmode.domain.business.entity.BusinessType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BusinessListResponse {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    List<BusinessResponse> businessList;

    @Getter
    @Builder
    public static class BusinessResponse{
        Long businessId;
        Long ownerId;
        String businessName;
        BusinessType businessType;
    }
}

