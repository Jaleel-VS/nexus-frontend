package com.nexus.nexusapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.nexusapi.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    
}
