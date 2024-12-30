package com.example.yourmode.domain.customer.service;

import com.example.yourmode.domain.customer.dto.request.CustomerRequestDto;
import com.example.yourmode.domain.customer.dto.response.CustomerIdResponseDto;
import com.example.yourmode.domain.customer.dto.response.CustomerListResponseDto;
import com.example.yourmode.domain.customer.dto.response.CustomerInfoResponseDto;
import com.example.yourmode.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;

public interface CustomerOwnerService {
    CustomerIdResponseDto createCustomer(Member member, CustomerRequestDto request);
    CustomerIdResponseDto updateCustomer(Long customerId, CustomerRequestDto request);
    CustomerIdResponseDto deleteCustomer(Long customerId);
    CustomerListResponseDto getCustomerList(Member member, Pageable pageable);
    CustomerInfoResponseDto getCustomerDetail(Long customerId);
}
