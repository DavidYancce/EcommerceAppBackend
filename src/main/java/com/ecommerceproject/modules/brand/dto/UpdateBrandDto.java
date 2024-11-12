package com.ecommerceproject.modules.brand.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateBrandDto {
    private Long companyId;

    private String code;

    private String name;

    private String logoUrl;

    private MultipartFile logo;
}
