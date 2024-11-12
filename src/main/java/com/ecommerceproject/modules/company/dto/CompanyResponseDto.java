package com.ecommerceproject.modules.company.dto;

import com.ecommerceproject.modules.brand.dto.BrandResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
public class CompanyResponseDto {
    private Long id;

    private String name;

    private String ruc;

    private String address;

    private String email;

    private String phoneNumber;

    private String website;

    private String logoUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Set<BrandResponseDto> brands;
}
