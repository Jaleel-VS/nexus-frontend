package com.nexus.vouchers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexus.vouchers.models.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    
}
