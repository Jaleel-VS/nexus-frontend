package com.nexus.vouchers.services;

import com.nexus.vouchers.models.Supplier;
import com.nexus.vouchers.models.Voucher;
import com.nexus.vouchers.models.enums.VoucherStatus;
import com.nexus.vouchers.repositories.VoucherRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService extends UserService<Supplier> {
    @Autowired
    private VoucherRepository voucherRepository;

    // Validate a voucher (check if it's not expired and not previously redeemed).
    public Voucher validateVoucher(Long voucherId) {
        // TODO: Implement
        return null;
    }

    // Mark voucher as redeemed
    public Voucher redeemVoucher(Long voucherId, VoucherStatus voucherStatus) {
        // TODO: Implement
        return null;
    }

    // Get all vouchers that have been redeemed at a supplier.
    public Iterable<Voucher> getRedeemedVouchers(Long supplierId) {
        Iterable<Voucher> vouchers = voucherRepository.findAllBySupplierId(supplierId);

        // filter by redeemed vouchers
        Stream<Voucher> voucherStream = StreamSupport.stream(vouchers.spliterator(), false);
        return voucherStream.filter(voucher -> voucher.getVoucherStatus() == VoucherStatus.REDEEMED)
                .collect(Collectors.toList());
    }


}
