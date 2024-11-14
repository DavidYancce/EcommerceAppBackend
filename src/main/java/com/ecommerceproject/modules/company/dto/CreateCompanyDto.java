package com.ecommerceproject.modules.company.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class CreateCompanyDto {
    private String name;

    private String ruc;

    private String address;

    private String email;

    private String phoneNumber;

    private String website;

    private MultipartFile logo;
}
