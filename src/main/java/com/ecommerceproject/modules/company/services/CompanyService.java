package com.ecommerceproject.modules.company.services;

import com.ecommerceproject.modules.brand.dto.BrandResponseDto;
import com.ecommerceproject.modules.brand.entity.Brand;
import com.ecommerceproject.modules.brand.repositories.BrandRepository;
import com.ecommerceproject.modules.brand.services.BrandService;
import com.ecommerceproject.modules.company.dto.CompanyResponseDto;
import com.ecommerceproject.modules.company.dto.CreateCompanyDto;
import com.ecommerceproject.modules.company.dto.UpdateCompanyDto;
import com.ecommerceproject.modules.company.entity.Company;
import com.ecommerceproject.modules.company.repositories.CompanyRepository;
import com.ecommerceproject.providers.storage.s3.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandService brandService;

    @Autowired
    private S3Service s3Service;

    public CompanyResponseDto createCompany(CreateCompanyDto createCompanyDto) throws IOException {
        Company company = new Company();
        company.setName(createCompanyDto.getName());
        company.setRuc(createCompanyDto.getRuc());
        company.setAddress(createCompanyDto.getAddress());
        company.setEmail(createCompanyDto.getEmail());
        company.setPhoneNumber(createCompanyDto.getPhoneNumber());
        company.setWebsite(createCompanyDto.getWebsite());

        if(createCompanyDto.getLogo() != null) {
            String url = s3Service.uploadFile(createCompanyDto.getLogo());
            company.setLogoUrl(url);
        }

        Company savedCompany = companyRepository.save(company);

        return convertToCompanyResponseDto(savedCompany);
    }

    public List<CompanyResponseDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertToCompanyResponseDto)
                .collect(Collectors.toList());
    }

    public CompanyResponseDto getCompanyById(Long id){
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compañia no encontrada con el ID: " + id));
        return convertToCompanyResponseDto(company);
    }

    public CompanyResponseDto updateCompany(Long id, UpdateCompanyDto updateCompanyDto) throws IOException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compañia no encontrada con el ID: " + id));

        company.setName(updateCompanyDto.getName());
        company.setRuc(updateCompanyDto.getRuc());
        company.setAddress(updateCompanyDto.getAddress());
        company.setEmail(updateCompanyDto.getEmail());
        company.setPhoneNumber(updateCompanyDto.getPhoneNumber());
        company.setWebsite(updateCompanyDto.getWebsite());

        if(updateCompanyDto.getLogo() != null) {
            if(updateCompanyDto.getLogoUrl() != null){
                String key = extractKeyFromUrl(updateCompanyDto.getLogoUrl());
                s3Service.deleteFile(key);
            }
            String url = s3Service.uploadFile(updateCompanyDto.getLogo());
            company.setLogoUrl(url);
        }

        Company updatedCompany = companyRepository.save(company);

        return convertToCompanyResponseDto(updatedCompany);
    }

    public void deleteCompany(Long id){
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Compañia no encontrada con el ID: " + id);
        }
        companyRepository.deleteById(id);
    }

    private String extractKeyFromUrl(String url){
        String[] partes = url.split(".com/");

        if (partes.length > 1) {
            return partes[1];
        } else {
            return null;
        }
    }

    private CompanyResponseDto convertToCompanyResponseDto(Company company) {
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        companyResponseDto.setId(company.getId());
        companyResponseDto.setName(company.getName());
        companyResponseDto.setRuc(company.getRuc());
        companyResponseDto.setAddress(company.getAddress());
        companyResponseDto.setEmail(company.getEmail());
        companyResponseDto.setPhoneNumber(company.getPhoneNumber());
        companyResponseDto.setWebsite(company.getWebsite());
        companyResponseDto.setLogoUrl(company.getLogoUrl());
        companyResponseDto.setCreatedAt(company.getCreatedAt());
        companyResponseDto.setUpdatedAt(company.getUpdatedAt());

        Set<BrandResponseDto> brands = brandRepository.findByCompany(company)
                .stream()
                .map(brand -> brandService.convertToBrandResponseDto(brand))
                .collect(Collectors.toSet());

        companyResponseDto.setBrands(brands);

        return companyResponseDto;
    }
}
