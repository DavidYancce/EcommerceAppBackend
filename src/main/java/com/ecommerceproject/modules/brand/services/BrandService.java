package com.ecommerceproject.modules.brand.services;

import com.ecommerceproject.modules.brand.dto.BrandResponseDto;
import com.ecommerceproject.modules.brand.dto.CreateBrandDto;
import com.ecommerceproject.modules.brand.dto.UpdateBrandDto;
import com.ecommerceproject.modules.brand.entity.Brand;
import com.ecommerceproject.modules.brand.repositories.BrandRepository;
import com.ecommerceproject.modules.company.entity.Company;
import com.ecommerceproject.modules.company.repositories.CompanyRepository;
import com.ecommerceproject.providers.storage.s3.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private S3Service s3Service;

    public BrandResponseDto createBrand(CreateBrandDto createBrandDto) throws IOException {
        final Long id = createBrandDto.getCompanyId();
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Compañía no encontrada con el ID: " + id);
        }

        Brand brand = new Brand();
        brand.setCompany(new Company(id));
        brand.setCode(createBrandDto.getCode());
        brand.setName(createBrandDto.getName());

        if(createBrandDto.getLogo() != null) {
            String url = s3Service.uploadFile(createBrandDto.getLogo());
            brand.setLogoUrl(url);
        }

        Brand savedBrand = brandRepository.save(brand);

        return convertToBrandResponseDto(savedBrand);
    }

    public List<BrandResponseDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::convertToBrandResponseDto)
                .collect(Collectors.toList());
    }

    public BrandResponseDto getBrandById(Long id){
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con el ID: " + id));
        return convertToBrandResponseDto(brand);
    }

    public BrandResponseDto updateBrand(Long id, UpdateBrandDto updateBrandDto) throws IOException {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con el ID: " + id));

        brand.setCode(updateBrandDto.getCode());
        brand.setName(updateBrandDto.getName());

        if(updateBrandDto.getLogo() != null) {
            if(updateBrandDto.getLogoUrl() != null){
                String key = extractKeyFromUrl(updateBrandDto.getLogoUrl());
                s3Service.deleteFile(key);
            }
            String url = s3Service.uploadFile(updateBrandDto.getLogo());
            brand.setLogoUrl(url);
        }

        Brand updatedBrand = brandRepository.save(brand);

        return convertToBrandResponseDto(updatedBrand);
    }

    public void deleteBrand(Long id){
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("Marca no encontrada con el ID: " + id);
        }
        brandRepository.deleteById(id);
    }

    private String extractKeyFromUrl(String url){
        String[] partes = url.split(".com/");

        if (partes.length > 1) {
            return partes[1];
        } else {
            return null;
        }
    }

    public BrandResponseDto convertToBrandResponseDto(Brand brand) {
        BrandResponseDto brandResponseDto = new BrandResponseDto();
        brandResponseDto.setId(brand.getId());
        brandResponseDto.setCode(brand.getCode());
        brandResponseDto.setName(brand.getName());
        brandResponseDto.setLogoUrl(brand.getLogoUrl());
        brandResponseDto.setCreatedAt(brand.getCreatedAt());
        brandResponseDto.setUpdatedAt(brand.getUpdatedAt());
        return brandResponseDto;
    }
}
