package com.nexus.nexusapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.nexusapi.model.Voucher;
import com.nexus.nexusapi.model.VoucherRequest;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    Optional<Voucher> findByVoucherQRCodeString(String voucherPin);

    Optional<Voucher> findByVoucherRequest_Id(Long id);

   
    
}
