package com.nexus.nexusapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.nexusapi.model.VoucherTransaction;

@Repository
public interface VoucherTransactionRepository extends JpaRepository<VoucherTransaction, Long> {
    
}
