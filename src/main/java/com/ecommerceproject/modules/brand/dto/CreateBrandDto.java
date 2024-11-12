package com.ecommerceproject.modules.brand.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class CreateBrandDto {
    @NotNull
    private Long companyId;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private MultipartFile logo;
}
