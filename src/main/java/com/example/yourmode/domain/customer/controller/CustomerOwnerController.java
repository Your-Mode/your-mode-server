package com.example.yourmode.domain.customer.controller;

import com.example.yourmode.domain.customer.dto.request.CustomerRequestDto;
import com.example.yourmode.domain.customer.dto.response.CustomerIdResponseDto;
import com.example.yourmode.domain.customer.dto.response.CustomerListResponseDto;
import com.example.yourmode.domain.customer.dto.response.CustomerInfoResponseDto;
import com.example.yourmode.domain.customer.service.CustomerOwnerService;
import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.global.common.base.BaseResponse;
import com.example.yourmode.global.common.validation.annotation.VerifyBusinessOwner;
import com.example.yourmode.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "업주용 고객 API", description = "업주용 고객 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/customers")
public class CustomerOwnerController {

    private final CustomerOwnerService customerOwnerService;

    // todo: 다른 사람의 비즈니스를 아예 볼 수가 없다면 굳이 이렇게 할 필요가 없음
    // todo: 그런데 다른 사람의 비즈니스를 볼 수 있다면, 이렇게 하는게 맞음

    @Operation(summary = "고객 등록", description = "새로운 고객을 등록함")
    //구매를 한 고객만 등록 가능, 고객은 서비스의 회원 일 수도 있고 아닐 수도 있음, 나중에 가입 하면 동시에 고객 연동 됨 (전화번호 통해서)
    @PostMapping("")
    public BaseResponse<CustomerIdResponseDto> registerCustomer(
            @CurrentMember Member member,
            @RequestBody CustomerRequestDto request) {

        return BaseResponse.onSuccess(customerOwnerService.createCustomer(member, request));
    }

    @Operation(summary = "고객 수정", description = "기존 고객의 정보를 수정함")
    @PatchMapping("/{customerId}")
    public BaseResponse<CustomerIdResponseDto>  updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequestDto request) {

        return BaseResponse.onSuccess(customerOwnerService.updateCustomer(customerId, request));
    }

    @Operation(summary = "고객 삭제", description = "고객 정보를 삭제함")
    @DeleteMapping("/{customerId}")
    public BaseResponse<CustomerIdResponseDto> deleteCustomer(
            @PathVariable Long customerId) {

        return BaseResponse.onSuccess(customerOwnerService.deleteCustomer(customerId));
    }

    @Operation(summary = "고객 리스트 조회", description = "전체 고객 리스트를 조회함")
    @GetMapping
    public BaseResponse<CustomerListResponseDto> getCustomerList(
            @CurrentMember Member member,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ) {

        return BaseResponse.onSuccess(customerOwnerService.getCustomerList(member, pageable));
    }

    @Operation(summary = "고객 정보 상세 조회", description = "특정 고객의 상세 정보를 조회함")
    @GetMapping("/{customerId}")
    public BaseResponse<CustomerInfoResponseDto> getCustomerDetail(@PathVariable Long customerId) {

        return BaseResponse.onSuccess(customerOwnerService.getCustomerDetail(customerId));
    }
}
