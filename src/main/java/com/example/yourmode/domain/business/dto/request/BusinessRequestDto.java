package com.example.yourmode.domain.business.dto.request;

import com.example.yourmode.domain.business.entity.BusinessType;
import jakarta.validation.constraints.NotNull;

public record BusinessRequestDto (
        BusinessType businessType,
        @NotNull
        String name,
        String address,
        String siteUrl
){
}
