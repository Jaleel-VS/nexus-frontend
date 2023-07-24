package com.nexus.vouchers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexus.vouchers.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
}
