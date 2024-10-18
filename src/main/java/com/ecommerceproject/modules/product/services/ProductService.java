package com.ecommerceproject.modules.product.services;

import com.ecommerceproject.modules.category.entity.Category;
import com.ecommerceproject.modules.category.repositories.CategoryRepository;
import com.ecommerceproject.modules.product.dto.ImageResponseDto;
import com.ecommerceproject.modules.product.dto.ProductResponseDto;
import com.ecommerceproject.modules.product.dto.CreateProductDto;
import com.ecommerceproject.modules.product.dto.UpdateProductDto;
import com.ecommerceproject.modules.product.entity.Image;
import com.ecommerceproject.modules.product.entity.Product;
import com.ecommerceproject.modules.product.entity.ProductCategory;
import com.ecommerceproject.modules.product.repositories.ImageRepository;
import com.ecommerceproject.modules.product.repositories.ProductCategoryRepository;
import com.ecommerceproject.modules.product.repositories.ProductRepository;
import com.ecommerceproject.providers.storage.s3.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private S3Service s3Service;

    public ProductResponseDto createProduct(CreateProductDto createProductDto) throws IOException {
        Product product = new Product();
        product.setCode(createProductDto.getCode());
        product.setName(createProductDto.getName());
        product.setStock(createProductDto.getStock());
        product.setPrice(createProductDto.getPrice());
        product.setDescription(createProductDto.getDescription());

        Product savedProduct = productRepository.save(product);

        for (Integer categoryId : createProductDto.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + categoryId));

            ProductCategory productCategory = new ProductCategory();
            productCategory.setProduct(savedProduct);
            productCategory.setCategory(category);
            productCategoryRepository.save(productCategory);
        }

        saveImages(savedProduct, createProductDto.getFiles());

        return convertToProductResponseDto(savedProduct);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> convertToProductResponseDto(product))
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDto> getProductById(Integer id) {
        return productRepository.findById(id)
                .map(product -> convertToProductResponseDto(product));
    }

    public ProductResponseDto updateProduct(Integer id, UpdateProductDto updateProductDto) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        product.setCode(updateProductDto.getCode());
        product.setName(updateProductDto.getName());
        product.setStock(updateProductDto.getStock());
        product.setPrice(updateProductDto.getPrice());
        product.setDescription(updateProductDto.getDescription());

        List<ProductCategory> existingProductCategories = productCategoryRepository.findByProduct(product);
        if (!existingProductCategories.isEmpty()) {
            productCategoryRepository.deleteAll(existingProductCategories);
        }

        for (Integer categoryId : updateProductDto.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + categoryId));

            ProductCategory productCategory = new ProductCategory();
            productCategory.setProduct(product);
            productCategory.setCategory(category);
            productCategoryRepository.save(productCategory);
        }

        saveImages(product, updateProductDto.getFiles());

        Product updatedProduct = productRepository.save(product);

        return convertToProductResponseDto(updatedProduct);
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        List<ProductCategory> productCategories = productCategoryRepository.findByProduct(product);
        if (!productCategories.isEmpty()) {
            productCategoryRepository.deleteAll(productCategories);
        }

        deleteImages(product);

        productRepository.delete(product);
    }

    private void saveImages(Product product, MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return;
        }

        boolean isFirstImage = true;

        for (MultipartFile file : files) {
            String url = s3Service.uploadFile(file);

            Image image = new Image();
            image.setProduct(product);
            image.setUrl(url);
            image.setIsMain(isFirstImage);
            imageRepository.save(image);

            isFirstImage = false;
        }
    }

    private void deleteImages(Product product) {
        List<Image> images = imageRepository.findByProduct(product);

        if (images == null || images.isEmpty()) {
            return;
        }

        for (Image image : images) {
            s3Service.deleteFile(image.getName());
            imageRepository.delete(image);
        }
    }

    private ProductResponseDto convertToProductResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        List<ProductCategory> productCategories = productCategoryRepository.findByProduct(product);
        List<Category> categories = productCategories.stream()
                .map(pc -> pc.getCategory().getId())
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id)))
                .collect(Collectors.toList());
        dto.setCategories(categories);

        List<Image> images = imageRepository.findByProduct(product);
        List<ImageResponseDto> imageDtos = images.stream().map(image -> {
            ImageResponseDto imageDto = new ImageResponseDto();
            imageDto.setId(image.getId());
            imageDto.setUrl(image.getUrl());
            imageDto.setIsMain(image.getIsMain());
            imageDto.setCreatedAt(image.getCreatedAt());
            imageDto.setUpdatedAt(image.getUpdatedAt());
            return imageDto;
        }).collect(Collectors.toList());

        dto.setFiles(imageDtos);

        return dto;
    }
}