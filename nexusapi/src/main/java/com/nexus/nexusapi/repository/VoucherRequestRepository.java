package com.nexus.nexusapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.nexusapi.model.VoucherRequest;

@Repository
public interface VoucherRequestRepository extends JpaRepository<VoucherRequest, Long> {
    
}
