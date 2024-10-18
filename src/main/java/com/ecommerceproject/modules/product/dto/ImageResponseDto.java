package com.ecommerceproject.modules.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ImageResponseDto {
    private Integer id;
    private String url;
    private Boolean isMain;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

