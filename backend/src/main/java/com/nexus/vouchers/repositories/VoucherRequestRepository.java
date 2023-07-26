package com.nexus.vouchers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexus.vouchers.models.VoucherRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRequestRepository extends JpaRepository<VoucherRequest, Long> {

    Iterable<VoucherRequest> findAllByInfluencerId(Long influencerId);

    Iterable<VoucherRequest> findAllByBrandId(Long brandId);

    Iterable<VoucherRequest> findAllBySupplierId(Long supplierId);
}
