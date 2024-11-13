package com.ecommerceproject.modules.company.entity;

import com.ecommerceproject.modules.brand.entity.Brand;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "TB_COMPANY", schema = "EXCHANGE_APP")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, length = 11, nullable = false)
    private String ruc;

    @Column(nullable = false)
    private String address;

    @Email(message = "Debe ser una dirección de correo electrónico con formato correcto")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 9, nullable = false)
    private String phoneNumber;

    @URL(message = "Debe ser un URL válido")
    @Column
    private String website;

    @URL(message = "Debe ser un URL válido")
    @Column
    private String logoUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Brand> brands;

    public Company(Long id) {
        this.id = id;
    }

    public Company() {
    }
}