package com.ecommerceproject.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "TB_COMPANY", schema = "EXCHANGE_APP")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ruc;
    private String address;
    private String email;
    private String phoneNumber;
    private String website;
    private String logoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Brand> brands;
}