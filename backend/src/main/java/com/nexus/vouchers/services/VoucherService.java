package com.nexus.vouchers.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nexus.vouchers.models.Voucher;
import com.nexus.vouchers.repositories.VoucherRepository;

public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    // get voucher by id
    public Optional<Voucher> getVoucher(Long voucherId) {
        return voucherRepository.findById(voucherId);
    }

    // get all vouchers
    public Iterable<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }
}
