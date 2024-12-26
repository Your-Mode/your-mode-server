package com.example.yourmode.domain.business.controller;

import com.example.yourmode.domain.business.dto.request.BusinessRequest;
import com.example.yourmode.domain.business.dto.response.BusinessIdResponse;
import com.example.yourmode.global.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "업주용 업체 API", description = "업주용 업체 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/businesses")
public class BusinessOwnerController {

    // 업장 등록
    @Operation(summary = "업장 등록", description = "새로운 업장을 등록함")
    @PostMapping
    public BaseResponse<BusinessIdResponse> registerBusiness(@RequestBody BusinessRequest request) {

        businessService.registerBusiness(request);

        return BaseResponse.onSuccess();
    }

    // 업장 수정
    @Operation(summary = "업장 수정", description = "기존 업장의 정보를 수정함")
    @PatchMapping("/{businessId}")
    public BaseResponse<BusinessIdResponse> updateBusiness(
            @PathVariable Long businessId,
            @RequestBody BusinessRequest request) {

        businessService.updateBusiness(businessId, request);

        return BaseResponse.onSuccess(updatedBusiness);
    }

    // 업장 삭제
    @Operation(summary = "업장 삭제", description = "업장 정보를 삭제함")
    @DeleteMapping("/{businessId}")
    public BaseResponse<BusinessIdResponse> deleteBusiness(@PathVariable Long businessId) {

        businessService.deleteBusiness(businessId);

        return BaseResponse.onSuccess();
    }

}
