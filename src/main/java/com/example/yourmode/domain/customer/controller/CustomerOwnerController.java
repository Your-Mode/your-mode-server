package com.example.yourmode.domain.customer.controller;

import com.example.yourmode.domain.customer.dto.request.CustomerRequest;
import com.example.yourmode.domain.customer.dto.response.CustomerIdResponse;
import com.example.yourmode.domain.customer.dto.response.CustomerListResponse;
import com.example.yourmode.domain.customer.dto.response.CustomerResponse;
import com.example.yourmode.global.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "업주용 고객 API", description = "업주용 고객 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/customers")
public class CustomerOwnerController {

    @Operation(summary = "고객 등록", description = "새로운 고객을 등록함")
    //구매를 한 고객만 등록 가능, 고객은 서비스의 회원 일 수도 있고 아닐 수도 있음, 나중에 가입 하면 동시에 고객 연동 됨 (전화번호 통해서)
    @PostMapping
    public BaseResponse<CustomerIdResponse> registerCustomer(@RequestBody CustomerRequest request) {

        Long customerId = customerService.registerCustomer(request);

        return BaseResponse.onSuccess();
    }

    @Operation(summary = "고객 수정", description = "기존 고객의 정보를 수정함")
    @PatchMapping("/{customerId}")
    public BaseResponse<CustomerIdResponse>  updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequest request) {

        CustomerResponseDto updatedCustomer = customerService.updateCustomer(customerId, request);

        return BaseResponse.onSuccess();
    }

    @Operation(summary = "고객 삭제", description = "고객 정보를 삭제함")
    @DeleteMapping("/{customerId}")
    public BaseResponse<CustomerIdResponse> deleteCustomer(@PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);

        return BaseResponse.onSuccess();
    }

    @Operation(summary = "고객 리스트 조회", description = "전체 고객 리스트를 조회함")
    @GetMapping
    public BaseResponse<CustomerListResponse> getCustomerList() {

        customerService.getCustomerList();

        return BaseResponse.onSuccess();
    }

    @Operation(summary = "고객 정보 상세 조회", description = "특정 고객의 상세 정보를 조회함")
    @GetMapping("/{customerId}")
    public BaseResponse<CustomerResponse> getCustomerDetail(@PathVariable Long customerId) {

        CustomerResponse customerDetail = customerService.getCustomerDetail(customerId);

        return BaseResponse.onSuccess();
    }
}
