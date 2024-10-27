package com.ecommerceproject.domain.model;
import com.ecommerceproject.modules.product.entity.Product;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SKU", schema = "EXCHANGE_APP")
public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private String code;
    private int stock;
    private String name;
    private double price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}