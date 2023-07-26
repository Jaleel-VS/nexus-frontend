package com.nexus.vouchers.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.nexus.vouchers.models.VoucherRequest;
import com.nexus.vouchers.repositories.VoucherRequestRepository;

public class VoucherRequestService {
    @Autowired
    private VoucherRequestRepository voucherRequestRepository;

    // get voucher by id
    public Optional<VoucherRequest> getVoucherRequest(Long voucherId) {
        return voucherRequestRepository.findById(voucherId);
    }

    // get all vouchers
    public Iterable<VoucherRequest> getAllVoucherRequests() {
        return voucherRequestRepository.findAll();
    }
}
