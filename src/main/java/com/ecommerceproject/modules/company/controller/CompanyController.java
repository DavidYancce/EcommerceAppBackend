package com.ecommerceproject.modules.company.controller;

import com.ecommerceproject.modules.company.dto.CompanyResponseDto;
import com.ecommerceproject.modules.company.dto.CreateCompanyDto;
import com.ecommerceproject.modules.company.dto.UpdateCompanyDto;
import com.ecommerceproject.modules.company.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(
            @RequestPart("company") @Valid CreateCompanyDto createCompanyDto,
            @RequestPart(value = "logo", required = false) MultipartFile logo) throws IOException {
        if (logo != null) {
            createCompanyDto.setLogo(logo);
        }
        return ResponseEntity.ok(companyService.createCompany(createCompanyDto));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(
            @PathVariable Long id,
            @RequestPart("company") @Valid UpdateCompanyDto updateCompanyDto,
            @RequestPart(value = "logo", required = false) MultipartFile logo) throws IOException {
        if (logo != null) {
            updateCompanyDto.setLogo(logo);
        }
        return ResponseEntity.ok(companyService.updateCompany(id, updateCompanyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
