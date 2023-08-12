package com.nexus.nexusapi.repository;


import com.nexus.nexusapi.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
