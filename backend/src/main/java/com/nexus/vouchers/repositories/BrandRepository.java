package com.nexus.vouchers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexus.vouchers.models.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
