package com.nexus.vouchers.services;

import com.nexus.vouchers.models.Influencer;
import com.nexus.vouchers.models.Voucher;
import com.nexus.vouchers.models.VoucherRequest;
import com.nexus.vouchers.repositories.VoucherRepository;
import com.nexus.vouchers.repositories.VoucherRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfluencerService extends UserService<Influencer> {
    @Autowired
    VoucherRequestRepository voucherRequestRepository;

    @Autowired
    VoucherRepository voucherRepository;

    // request voucher
    public VoucherRequest requestVoucher(VoucherRequest voucherRequest) {
        return voucherRequestRepository.save(voucherRequest);
    }

    // Get all voucher requests made by an influencer.
    public Iterable<VoucherRequest> getVoucherRequests(Long influencerId) {
        return voucherRequestRepository.findAllByInfluencerId(influencerId);
    }

    // Get all voucher assigned to an influencer.
    public Iterable<Voucher> getVouchers(Long influencerId) {
        return voucherRepository.findAllByInfluencerId(influencerId);
    }

    // redeem a voucher at a supplier
    public void redeemVoucher(Long voucherId, Long supplierId) {
  //    TODO: implement
    }



}
