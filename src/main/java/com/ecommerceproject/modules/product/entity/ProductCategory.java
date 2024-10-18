package com.ecommerceproject.modules.product.entity;

import com.ecommerceproject.modules.category.entity.Category;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_PRODUCT_CATEGORY", schema = "EXCHANGE_APP")
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
