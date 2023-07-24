package com.nexus.vouchers.services;

import com.nexus.vouchers.models.Influencer;
import com.nexus.vouchers.models.VoucherRequest;
import com.nexus.vouchers.repositories.InfluencerRepository;
import com.nexus.vouchers.repositories.VoucherRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfluencerService extends UserService<Influencer> {
    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    VoucherRequestRepository voucherRequestRepository;

    // request voucher
    public VoucherRequest requestVoucher(VoucherRequest voucherRequest) {
        return voucherRequestRepository.save(voucherRequest);
    }

    // Get all voucher requests assigned to an influencer.
    public Iterable<VoucherRequest> getVouchers(Long influencerId) {
        return voucherRequestRepository.findAllByInfluencerId(influencerId);
    }



}
