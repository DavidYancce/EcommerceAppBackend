package com.ecommerceproject.modules.product.repositories;

import com.ecommerceproject.modules.product.entity.Image;
import com.ecommerceproject.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByProduct(Product product);
}

