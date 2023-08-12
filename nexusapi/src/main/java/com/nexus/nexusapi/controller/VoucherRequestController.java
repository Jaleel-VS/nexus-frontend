package com.nexus.nexusapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexus.nexusapi.model.VoucherRequest;
import com.nexus.nexusapi.service.VoucherRequestService;

import java.util.List;

@RestController
@RequestMapping("/api/voucher-requests")
public class VoucherRequestController {

    @Autowired
    private VoucherRequestService voucherRequestService;

    // Create a new Voucher Request
    @PostMapping
    public ResponseEntity<VoucherRequest> createVoucherRequest(@RequestBody VoucherRequest request) {
        VoucherRequest createdRequest = voucherRequestService.createVoucherRequest(request);
        return ResponseEntity.ok(createdRequest);
    }

    // Approve a Voucher Request
    @PutMapping("/{requestId}/approve")
    public ResponseEntity<VoucherRequest> approveVoucherRequest(@PathVariable Long requestId) {
        VoucherRequest approvedRequest = voucherRequestService.approveVoucherRequest(requestId);
        return ResponseEntity.ok(approvedRequest);
    }

    // Deny a Voucher Request
    @PutMapping("/{requestId}/deny")
    public ResponseEntity<VoucherRequest> denyVoucherRequest(@PathVariable Long requestId) {
        VoucherRequest deniedRequest = voucherRequestService.denyVoucherRequest(requestId);
        return ResponseEntity.ok(deniedRequest);
    }

    // Get a specific Voucher Request by ID
    @GetMapping("/{requestId}")
    public ResponseEntity<VoucherRequest> getVoucherRequestById(@PathVariable Long requestId) {
        VoucherRequest request = voucherRequestService.getVoucherRequestById(requestId);
        return ResponseEntity.ok(request);
    }

    // Get all Voucher Requests
    @GetMapping
    public ResponseEntity<List<VoucherRequest>> getAllVoucherRequests() {
        List<VoucherRequest> requests = voucherRequestService.getAllVoucherRequests();
        return ResponseEntity.ok(requests);
    }

    // Additional endpoints can be added as needed, such as filtering requests by status, brand, influencer, etc.
}

