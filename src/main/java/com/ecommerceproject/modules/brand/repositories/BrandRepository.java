package com.ecommerceproject.modules.brand.repositories;

import com.ecommerceproject.modules.brand.entity.Brand;
import com.ecommerceproject.modules.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByCompany(Company company);
}
