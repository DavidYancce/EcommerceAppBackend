package com.ecommerceproject.modules.product.entity;

import com.ecommerceproject.modules.category.entity.Category;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_PRODUCT_CATEGORY", schema = "EXCHANGE_APP")
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
