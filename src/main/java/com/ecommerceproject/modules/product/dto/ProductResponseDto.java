package com.ecommerceproject.modules.product.dto; // Cambia el paquete según la estructura de tu proyecto

import com.ecommerceproject.modules.category.entity.Category;
import com.ecommerceproject.modules.product.entity.Image;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ProductResponseDto {
    private Integer id;

    private String code;

    private Integer stock;

    private String name;

    private BigDecimal price;

    private String description;

    private List<Category> categories;

    private List<ImageResponseDto> files;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
