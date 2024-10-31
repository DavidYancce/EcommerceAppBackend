package com.ecommerceproject.modules.category.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CategoryResponseDto {
    private Long id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
