package com.ecommerceproject.modules.category.services;

import com.ecommerceproject.modules.category.dto.CategoryResponseDto;
import com.ecommerceproject.modules.category.dto.CreateCategoryDto;
import com.ecommerceproject.modules.category.dto.UpdateCategoryDto;
import com.ecommerceproject.modules.category.entity.Category;
import com.ecommerceproject.modules.category.repositories.CategoryRepository;
import com.ecommerceproject.modules.product.entity.ProductCategory;
import com.ecommerceproject.modules.product.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public CategoryResponseDto createCategory(CreateCategoryDto CreateCategoryDto) {
        Category category = new Category();
        category.setName(CreateCategoryDto.getName());

        Category savedCategory = categoryRepository.save(category);

        return convertToCategoryResponseDto(savedCategory);
    }

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToCategoryResponseDto)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
        return convertToCategoryResponseDto(category);
    }

    public CategoryResponseDto updateCategory(Integer id, UpdateCategoryDto updateCategoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));

        category.setName(updateCategoryDto.getName());

        Category updatedCategory = categoryRepository.save(category);

        return convertToCategoryResponseDto(updatedCategory);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));

        List<ProductCategory> productCategories = productCategoryRepository.findByCategory(category);
        if (!productCategories.isEmpty()) {
            productCategoryRepository.deleteAll(productCategories);
        }

        categoryRepository.delete(category);
    }

    private CategoryResponseDto convertToCategoryResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setCreatedAt(category.getCreatedAt());
        categoryResponseDto.setUpdatedAt(category.getUpdatedAt());
        return categoryResponseDto;
    }
}

