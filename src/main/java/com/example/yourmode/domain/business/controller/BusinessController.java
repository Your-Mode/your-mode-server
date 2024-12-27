package com.example.yourmode.domain.business.controller;

import com.example.yourmode.domain.business.dto.response.BusinessListResponse;
import com.example.yourmode.domain.business.dto.response.BusinessResponse;
import com.example.yourmode.global.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/businesses")
@RequiredArgsConstructor
@Tag(name = "업장 API", description = "업장 관련 API")
public class BusinessController {
    // 업장 리스트 조회
    @Operation(summary = "업장 리스트 조회", description = "전체 업장 리스트를 조회함")
    @GetMapping
    public BaseResponse<BusinessListResponse> getBusinessList() {

        businessService.getBusinessList();

        return BaseResponse.onSuccess(businesses);
    }

    // 업장 상세 정보 조회
    @Operation(summary = "업장 상세 정보 조회", description = "특정 업장의 상세 정보를 조회함")
    @GetMapping("/{businessId}")
    public BaseResponse<BusinessResponse> getBusinessDetail(@PathVariable Long businessId) {

        businessService.getBusinessDetail(businessId);

        return BaseResponse.onSuccess(businessDetail);
    }
}
