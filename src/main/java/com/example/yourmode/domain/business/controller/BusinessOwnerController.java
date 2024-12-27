package com.example.yourmode.domain.business.controller;

import com.example.yourmode.domain.business.dto.request.BusinessRequestDto;
import com.example.yourmode.domain.business.dto.response.BusinessIdResponseDto;
import com.example.yourmode.domain.business.service.BusinessOwnerService;
import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.global.common.base.BaseResponse;
import com.example.yourmode.global.common.validation.annotation.VerifyBusinessOwner;
import com.example.yourmode.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "업주용 업체 API", description = "업주용 업체 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/businesses")
public class BusinessOwnerController {

    private final BusinessOwnerService businessOwnerService;

    // 업장 등록
    @Operation(summary = "업장 등록", description = "새로운 업장을 등록함")
    @PostMapping
    public BaseResponse<BusinessIdResponseDto> registerBusiness(
            @CurrentMember Member member,
            @RequestBody BusinessRequestDto request
    ) {

        return BaseResponse.onSuccess(
                businessOwnerService.createBusiness(member, request)
        );
    }

    // 업장 수정 todo: 멤버가 업주가 아닌 업장은 수정 삭제 못하도록 커스텀 어노테이션으로 처리
    @Operation(summary = "업장 수정", description = "기존 업장의 정보를 수정함")
    @PatchMapping("/{businessId}")
    @VerifyBusinessOwner
    public BaseResponse<BusinessIdResponseDto> updateBusiness(
            @PathVariable Long businessId,
            @RequestBody BusinessRequestDto request) {

        return BaseResponse.onSuccess(
                businessOwnerService.updateBusiness(businessId, request)
        );
    }

    // 업장 삭제
    @Operation(summary = "업장 삭제", description = "업장 정보를 삭제함")
    @DeleteMapping("/{businessId}")
    @VerifyBusinessOwner
    public BaseResponse<BusinessIdResponseDto> deleteBusiness(
            @PathVariable Long businessId)
    {

        return BaseResponse.onSuccess(
                businessOwnerService.deleteBusiness(businessId)
        );
    }

}
