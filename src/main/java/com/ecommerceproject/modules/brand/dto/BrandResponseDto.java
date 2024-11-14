package com.ecommerceproject.modules.brand.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BrandResponseDto {
    private Long id;

    private String code;

    private String name;

    private String logoUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
