package com.example.yourmode.domain.customer.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomerListResponseDto {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private List<CustomerResponse> customerList;

    @Getter
    @Builder
    public static class CustomerResponse {
        private final Long customerId;
        private final String nikeName;
        private final String phone;
    }
}
