package com.example.yourmode.domain.customer.mapper;

import com.example.yourmode.domain.customer.dto.request.CustomerRequestDto;
import com.example.yourmode.domain.customer.dto.response.CustomerInfoResponseDto;
import com.example.yourmode.domain.customer.dto.response.CustomerListResponseDto;
import com.example.yourmode.domain.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public static Customer toCustomer(CustomerRequestDto request) {
        return Customer.builder()
                .nikeName(request.nikeName())
                .phone(request.phone())
                .pdfFile(request.pdfFile())
                .imageFiles(request.imageFiles())
                .build();
    }

    public static CustomerInfoResponseDto toCustomerIInfoResponseDto(Customer customer) {
        return CustomerInfoResponseDto.builder()
                .nikeName(customer.getNikeName())
                .phone(customer.getPhone())
                .pdfFile(customer.getPdfFile())
                .imageFiles(customer.getImageFiles())
                .build();
    }

    public static CustomerListResponseDto toCustomerListResponseDto(Page<Customer> customers) {
        return CustomerListResponseDto.builder()
                .currentPage(customers.getNumber())
                .totalPages(customers.getTotalPages())
                .totalElements(customers.getTotalElements())
                .customerList(customers.getContent().stream().map(CustomerMapper::toCustomerResponseDto).collect(Collectors.toList()))
                .build();
    }

    public static CustomerListResponseDto.CustomerResponse toCustomerResponseDto(Customer customer) {
        return CustomerListResponseDto.CustomerResponse.builder()
                .customerId(customer.getId())
                .nikeName(customer.getNikeName())
                .phone(customer.getPhone())
                .build();
    }
}
