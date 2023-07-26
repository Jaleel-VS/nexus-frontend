package com.nexus.vouchers.services;

import com.nexus.vouchers.models.Brand;
import com.nexus.vouchers.models.Voucher;
import com.nexus.vouchers.models.VoucherRequest;
import com.nexus.vouchers.models.enums.RequestStatus;
import com.nexus.vouchers.repositories.VoucherRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends UserService<Brand> {
    @Autowired
    private VoucherRequestRepository voucherRequestRepository;


    //  Create a voucher in response to a voucher
    public Voucher createVoucher(Long voucherRequestId) {
        // TODO: implement
        return null;
    }

    // Get all voucher requests made to a brand.
    public Iterable<VoucherRequest> getVoucherRequests(Long brandId) {
        return voucherRequestRepository.findAllByBrandId(brandId);
    }

    // update voucher request status
    public VoucherRequest approveVoucherRequest(Long voucherRequestId, RequestStatus requestStatus) {
        VoucherRequest voucherRequest = voucherRequestRepository.findById(voucherRequestId).get();
        voucherRequest.setRequestStatus(requestStatus);
        return voucherRequestRepository.save(voucherRequest);
    }

    
       

}
