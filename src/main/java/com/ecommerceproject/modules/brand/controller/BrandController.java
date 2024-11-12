package com.ecommerceproject.modules.brand.controller;

import com.ecommerceproject.modules.brand.dto.BrandResponseDto;
import com.ecommerceproject.modules.brand.dto.CreateBrandDto;
import com.ecommerceproject.modules.brand.dto.UpdateBrandDto;
import com.ecommerceproject.modules.brand.services.BrandService;
import com.ecommerceproject.modules.company.dto.CreateCompanyDto;
import com.ecommerceproject.modules.company.dto.UpdateCompanyDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandResponseDto> createBrand(
            @RequestPart("brand") @Valid CreateBrandDto createBrandDto,
            @RequestPart(value = "logo", required = false) MultipartFile logo) throws IOException {
        if (logo != null) {
            createBrandDto.setLogo(logo);
        }
        return ResponseEntity.ok(brandService.createBrand(createBrandDto));
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDto> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BrandResponseDto> updateBrand(
            @PathVariable Long id,
            @RequestPart("brand") @Valid UpdateBrandDto updateBrandDto,
            @RequestPart(value = "logo", required = false) MultipartFile logo) throws IOException {
        if (logo != null) {
            updateBrandDto.setLogo(logo);
        }
        return ResponseEntity.ok(brandService.updateBrand(id, updateBrandDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
