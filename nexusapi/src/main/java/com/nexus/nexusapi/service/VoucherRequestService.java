package com.nexus.nexusapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.nexus.nexusapi.model.VoucherRequest;
import com.nexus.nexusapi.repository.VoucherRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoucherRequestService {

    @Autowired
    private VoucherRequestRepository voucherRequestRepository;

    // Create a new Voucher Request
    public VoucherRequest createVoucherRequest(VoucherRequest request) {
        request.setRequestDate(LocalDate.now());
        request.setRequestStatus(VoucherRequest.RequestStatus.REQUESTED);
        return voucherRequestRepository.save(request);
    }

    // Approve a Voucher Request
    public VoucherRequest approveVoucherRequest(Long requestId) {
        VoucherRequest request = getVoucherRequestById(requestId);
        request.setRequestStatus(VoucherRequest.RequestStatus.APPROVED);
        return voucherRequestRepository.save(request);
    }

    // Deny a Voucher Request
    public VoucherRequest denyVoucherRequest(Long requestId) {
        VoucherRequest request = getVoucherRequestById(requestId);
        request.setRequestStatus(VoucherRequest.RequestStatus.DENIED);
        return voucherRequestRepository.save(request);
    }

    // Get a specific Voucher Request by ID
  public VoucherRequest getVoucherRequestById(Long requestId) {
    try {
        return voucherRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException());
    } catch (NotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
}

    // Get all Voucher Requests
    public List<VoucherRequest> getAllVoucherRequests() {
        return voucherRequestRepository.findAll();
    }

    // Additional methods can be added as needed, such as filtering requests by status, brand, influencer, etc.
}
