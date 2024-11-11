package com.ecommerceproject.modules.product.dto;

import com.ecommerceproject.modules.category.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ProductResponseDto {
    private Integer id;

    private String code;

    private String name;

    private String description;

    private List<Category> categories;

    private List<ImageResponseDto> files;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
