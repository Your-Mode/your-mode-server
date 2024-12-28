package com.example.yourmode.domain.business.service;

import com.example.yourmode.domain.business.dto.response.BusinessListResponse;
import com.example.yourmode.domain.business.dto.response.BusinessInfoResponse;
import org.springframework.data.domain.Pageable;


public interface BusinessService {
    BusinessListResponse getBusinessList(Pageable pageable);
    BusinessInfoResponse getBusinessDetail(Long businessId);
}
