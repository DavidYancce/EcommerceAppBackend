package com.ecommerceproject.modules.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class CreateProductDto {
    private String code;

    private String name;

    private String description;

    private List<Integer> categoryIds;

    private MultipartFile[] files;
}
