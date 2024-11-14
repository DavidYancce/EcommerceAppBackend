package com.ecommerceproject.modules.brand.entity;

import com.ecommerceproject.modules.company.entity.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "TB_BRAND", schema = "EXCHANGE_APP")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(unique = true, length = 6, nullable = false)
    private String code;

    @Column(unique = true, nullable = false)
    private String name;

    @URL(message = "Debe ser un URL válido")
    @Column(name = "logo_url")
    private String logoUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}