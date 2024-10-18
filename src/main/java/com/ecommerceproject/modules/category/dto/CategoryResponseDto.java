package com.ecommerceproject.modules.category.dto; // Cambia el paquete según la estructura de tu proyecto

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CategoryResponseDto {
    private Integer id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
