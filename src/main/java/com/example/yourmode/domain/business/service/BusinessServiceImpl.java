package com.example.yourmode.domain.business.service;

import com.example.yourmode.domain.business.dto.response.BusinessInfoResponse;
import com.example.yourmode.domain.business.dto.response.BusinessListResponse;
import com.example.yourmode.domain.business.entity.Business;
import com.example.yourmode.domain.business.mapper.BusinessMapper;
import com.example.yourmode.domain.business.repository.BusinessRepository;
import com.example.yourmode.domain.business.status.BusinessErrorStatus;
import com.example.yourmode.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessServiceImpl implements BusinessService{

    private final BusinessRepository businessRepository;

    @Override
    public BusinessListResponse getBusinessList(Pageable pageable) {

        Page<Business> businessList = businessRepository.findAll(pageable);

        return BusinessMapper.toBusinessListResponse(businessList);
    }

    @Override
    public BusinessInfoResponse getBusinessDetail(Long businessId) {

        Business business =  businessRepository.findById(businessId)
                .orElseThrow(() -> new RestApiException(BusinessErrorStatus.EMPTY_BUSINESS));

        return BusinessMapper.toBusinessInfoResponse(business);
    }
}
