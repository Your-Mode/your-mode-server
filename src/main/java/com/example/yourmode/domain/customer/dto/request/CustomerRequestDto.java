package com.example.yourmode.domain.customer.dto.request;

import java.util.List;

public record CustomerRequestDto(
    String nikeName,
    String phone,
    String memo,
    String pdfFile,
    List<String> imageFiles
) {
}
