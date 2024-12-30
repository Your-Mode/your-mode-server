package com.example.yourmode.domain.customer.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomerInfoResponseDto {
    private String nikeName;
    private String phone;
    private String memo;
    private String pdfFile;
    private List<String> imageFiles;
}
