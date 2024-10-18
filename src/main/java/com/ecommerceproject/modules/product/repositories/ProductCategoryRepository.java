package com.ecommerceproject.modules.product.repositories;

import com.ecommerceproject.modules.category.entity.Category;
import com.ecommerceproject.modules.product.entity.Product;
import com.ecommerceproject.modules.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByProduct(Product product);
    List<ProductCategory> findByCategory(Category category);
}
