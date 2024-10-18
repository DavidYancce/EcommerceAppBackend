package com.ecommerceproject.modules.product.repositories;

import com.ecommerceproject.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByCode(String code);
}
