package com.nexus.nexusapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.nexusapi.model.VoucherRequest;
import com.nexus.nexusapi.model.VoucherRequest.RequestStatus;

@Repository
public interface VoucherRequestRepository extends JpaRepository<VoucherRequest, Long> {

    List<VoucherRequest> findAllByBrandId(Long brandId);

    List<VoucherRequest> findAllByInfluencerId(Long influencerId);

    List<VoucherRequest> findAllByRequestStatus(RequestStatus status);

	List<VoucherRequest> findAllByRequestStatusAndBrandId(RequestStatus status, Long brandId);

    List<VoucherRequest> findAllByRequestStatusAndInfluencerId(RequestStatus status, Long influencerId);
    
}
