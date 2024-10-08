package com.ecommerceproject.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PAYMENT", schema = "EXCHANGE_APP")
@Data
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_method", nullable = false)
    private Integer paymentMethod;

    @Column(name = "payment_status", nullable = false)
    private Integer paymentStatus;

    @CreationTimestamp
    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime paymentDate;
}
