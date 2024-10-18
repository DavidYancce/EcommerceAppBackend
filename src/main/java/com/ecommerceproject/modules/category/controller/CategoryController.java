package com.ecommerceproject.modules.category.controller;

import com.ecommerceproject.modules.category.dto.CategoryResponseDto;
import com.ecommerceproject.modules.category.dto.CreateCategoryDto;
import com.ecommerceproject.modules.category.dto.UpdateCategoryDto;
import com.ecommerceproject.modules.category.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CreateCategoryDto CreateCategoryDto) {
        return ResponseEntity.ok(categoryService.createCategory(CreateCategoryDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Integer id, @RequestBody @Valid UpdateCategoryDto updateCategoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, updateCategoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
