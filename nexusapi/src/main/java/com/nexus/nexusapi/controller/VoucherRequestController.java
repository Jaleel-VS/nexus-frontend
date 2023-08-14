package com.nexus.nexusapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexus.nexusapi.dto.request.VoucherRequestRequestDTO;
import com.nexus.nexusapi.dto.response.VoucherRequestResponseDTO;
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
    public ResponseEntity<VoucherRequestResponseDTO> createVoucherRequest(@RequestBody VoucherRequestRequestDTO requestDTO) {
        VoucherRequest createdRequest = voucherRequestService.createVoucherRequest(requestDTO);
        return ResponseEntity.ok(voucherRequestService.convertToDTO(createdRequest));
   
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
    public ResponseEntity<VoucherRequestResponseDTO> getVoucherRequestById(@PathVariable Long requestId) {
        VoucherRequestResponseDTO voucherRequest = voucherRequestService.getVoucherRequestById(requestId);
        return ResponseEntity.ok(voucherRequest);
    }

    // Get all Voucher Requests
    @GetMapping
    public ResponseEntity<List<VoucherRequestResponseDTO>> getAllVoucherRequests() {
        List<VoucherRequestResponseDTO> voucherRequests = voucherRequestService.getAllVoucherRequests();
        return ResponseEntity.ok(voucherRequests);
    }

    // Get all Voucher Requests by Brand ID
    @GetMapping("/brands/{brandId}")
    public ResponseEntity<List<VoucherRequestResponseDTO>> getAllVoucherRequestsByBrandId(@PathVariable Long brandId) {
        List<VoucherRequestResponseDTO> voucherRequests = voucherRequestService.getAllVoucherRequestsByBrandId(brandId);
        return ResponseEntity.ok(voucherRequests);
    }

    // Get all Voucher Requests by Influencer ID
    @GetMapping("/influencers/{influencerId}")
    public ResponseEntity<List<VoucherRequestResponseDTO>> getAllVoucherRequestsByInfluencerId(@PathVariable Long influencerId) {
        List<VoucherRequestResponseDTO> voucherRequests = voucherRequestService.getAllVoucherRequestsByInfluencerId(influencerId);
        return ResponseEntity.ok(voucherRequests);
    }

    // Get all Voucher Requests by Request Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<VoucherRequestResponseDTO>> getAllVoucherRequestsByStatus(@PathVariable VoucherRequest.RequestStatus status) {
        List<VoucherRequestResponseDTO> voucherRequests = voucherRequestService.getAllVoucherRequestsByStatus(status);
        return ResponseEntity.ok(voucherRequests);
    }

    // Get all Voucher Requests by Request Status and Brand ID
    @GetMapping("/status/{status}/brands/{brandId}")
    public ResponseEntity<List<VoucherRequestResponseDTO>> getAllVoucherRequestsByStatusAndBrandId(@PathVariable VoucherRequest.RequestStatus status, @PathVariable Long brandId) {
        List<VoucherRequestResponseDTO> voucherRequests = voucherRequestService.getAllVoucherRequestsByStatusAndBrandId(status, brandId);
        return ResponseEntity.ok(voucherRequests);
    }

    // Get all Voucher Requests by Request Status and Influencer ID
    @GetMapping("/status/{status}/influencers/{influencerId}")
    public ResponseEntity<List<VoucherRequestResponseDTO>> getAllVoucherRequestsByStatusAndInfluencerId(@PathVariable VoucherRequest.RequestStatus status, @PathVariable Long influencerId) {
        List<VoucherRequestResponseDTO> voucherRequests = voucherRequestService.getAllVoucherRequestsByStatusAndInfluencerId(status, influencerId);
        return ResponseEntity.ok(voucherRequests);
    }
    

    // Additional endpoints can be added as needed, such as filtering requests by status, brand, influencer, etc.
}

