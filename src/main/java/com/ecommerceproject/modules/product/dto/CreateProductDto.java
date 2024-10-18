package com.ecommerceproject.modules.product.dto; // Cambia el paquete según la estructura de tu proyecto

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class CreateProductDto {
    private String code;

    private Integer stock;

    private String name;

    private BigDecimal price;

    private String description;

    private List<Integer> categoryIds;

    private MultipartFile[] files;
}
