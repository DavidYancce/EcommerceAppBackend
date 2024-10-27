package com.ecommerceproject.domain.model;

import com.ecommerceproject.modules.product.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_CART_DETAIL", schema = "EXCHANGE_APP")
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, scale = 2)
    private BigDecimal unitPrice;
}
