package com.ecommerceproject.modules.product.controller;

import com.ecommerceproject.modules.product.dto.ProductResponseDto;
import com.ecommerceproject.modules.product.dto.CreateProductDto;
import com.ecommerceproject.modules.product.dto.UpdateProductDto;
import com.ecommerceproject.modules.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
//    private ProductService productService;

//    @PostMapping
//    public ResponseEntity<ProductResponseDto> createProduct(
//            @RequestPart("product") @Valid CreateProductDto createProductDto,
//            @RequestPart("files") MultipartFile[] files) throws IOException {
//
//        createProductDto.setFiles(files);
//        ProductResponseDto createdProduct = productService.createProduct(createProductDto);
//        return ResponseEntity.ok(createdProduct);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
//        List<ProductResponseDto> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Integer id) {
//        Optional<ProductResponseDto> product = productService.getProductById(id);
//        return product.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductResponseDto> updateProduct(
//            @PathVariable Integer id,
//            @RequestPart("product") @Valid UpdateProductDto updateProductDto,
//            @RequestPart("files") MultipartFile[] files) throws IOException {
//
//        updateProductDto.setFiles(files);
//        ProductResponseDto updatedProduct = productService.updateProduct(id, updateProductDto);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
}