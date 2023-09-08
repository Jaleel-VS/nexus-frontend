package com.nexus.nexusapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexusapi.dto.request.VoucherRequestRequestDTO;
import com.nexus.nexusapi.dto.response.VoucherRequestResponseDTO;
import com.nexus.nexusapi.exceptions.NotFoundException;
import com.nexus.nexusapi.model.Brand;
import com.nexus.nexusapi.model.Influencer;
import com.nexus.nexusapi.model.Product;
import com.nexus.nexusapi.model.VoucherRequest;
import com.nexus.nexusapi.repository.BrandRepository;
import com.nexus.nexusapi.repository.InfluencerRepository;
import com.nexus.nexusapi.repository.ProductRepository;
import com.nexus.nexusapi.repository.VoucherRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoucherRequestService {

    @Autowired
    private VoucherRequestRepository voucherRequestRepository;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ProductRepository productRepository;

    // Create a new Voucher Request
    public VoucherRequest createVoucherRequest(VoucherRequestRequestDTO requestDTO) {

        VoucherRequest voucherRequest = new VoucherRequest();

        Influencer influencer = influencerRepository.findById(requestDTO.getInfluencerId())
                .orElseThrow(() -> new NotFoundException("Influencer not found"));
        Brand brand = brandRepository.findById(requestDTO.getBrandId())
                .orElseThrow(() -> new NotFoundException("Brand not found"));
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        voucherRequest.setInfluencer(influencer);
        voucherRequest.setBrand(brand);
        voucherRequest.setProduct(product);
        voucherRequest.setRequestDate(LocalDate.now());
        voucherRequest.setRequestStatus(VoucherRequest.RequestStatus.REQUESTED);
        voucherRequest.setWalletAddress(requestDTO.getWalletAddress());
        return voucherRequestRepository.save(voucherRequest);
    }

    // Approve a Voucher Request
    public VoucherRequest approveVoucherRequest(Long requestId) {
        VoucherRequest voucherRequest = voucherRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Voucher Request not found"));
        voucherRequest.setRequestStatus(VoucherRequest.RequestStatus.APPROVED);
        return voucherRequestRepository.save(voucherRequest);
    }

    // Deny a Voucher Request
    public VoucherRequest denyVoucherRequest(Long requestId) {
        VoucherRequest voucherRequest = voucherRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Voucher Request not found"));
        voucherRequest.setRequestStatus(VoucherRequest.RequestStatus.DENIED);
        return voucherRequestRepository.save(voucherRequest);
        
    }

    // Get a specific Voucher Request by ID
  public VoucherRequestResponseDTO getVoucherRequestById(Long requestId) {
        VoucherRequest voucherRequest = voucherRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Voucher Request not found"));
        return convertToDTO(voucherRequest);
}

    // Get all Voucher Requests
    public List<VoucherRequestResponseDTO> getAllVoucherRequests() {
        List<VoucherRequest> voucherRequests = voucherRequestRepository.findAll();
        return voucherRequests.stream().map(this::convertToDTO).toList();
    }

    // Get all Voucher Requests by brandID
    public List<VoucherRequestResponseDTO> getAllVoucherRequestsByBrandId(Long brandId) {
        List<VoucherRequest> voucherRequests = voucherRequestRepository.findAllByBrandId(brandId);
        return voucherRequests.stream().map(this::convertToDTO).toList();
    }

    // Get all Voucher Requests by influencerID
    public List<VoucherRequestResponseDTO> getAllVoucherRequestsByInfluencerId(Long influencerId) {
        List<VoucherRequest> voucherRequests = voucherRequestRepository.findAllByInfluencerId(influencerId);
        return voucherRequests.stream().map(this::convertToDTO).toList();
    }

    // Get all Voucher Requests by status
    public List<VoucherRequestResponseDTO> getAllVoucherRequestsByStatus(VoucherRequest.RequestStatus status) {
        List<VoucherRequest> voucherRequests = voucherRequestRepository.findAllByRequestStatus(status);
        return voucherRequests.stream().map(this::convertToDTO).toList();
    }

    // Get all Voucher Requests by status and brandID
    public List<VoucherRequestResponseDTO> getAllVoucherRequestsByStatusAndBrandId(VoucherRequest.RequestStatus status, Long brandId) {
        List<VoucherRequest> voucherRequests = voucherRequestRepository.findAllByRequestStatusAndBrandId(status, brandId);
        return voucherRequests.stream().map(this::convertToDTO).toList();
    }

    // Get all Voucher Requests by status and influencerID
    public List<VoucherRequestResponseDTO> getAllVoucherRequestsByStatusAndInfluencerId(VoucherRequest.RequestStatus status, Long influencerId) {
        List<VoucherRequest> voucherRequests = voucherRequestRepository.findAllByRequestStatusAndInfluencerId(status, influencerId);
        return voucherRequests.stream().map(this::convertToDTO).toList();
    }


    // Additional methods can be added as needed, such as filtering requests by status, brand, influencer, etc.

    // convert to VoucherRequestResponseDTO
    public VoucherRequestResponseDTO convertToDTO(VoucherRequest voucherRequest) {
        VoucherRequestResponseDTO dto = new VoucherRequestResponseDTO();
        dto.setId(voucherRequest.getId());
        dto.setInfluencerId(voucherRequest.getInfluencer().getId());
        dto.setBrandId(voucherRequest.getBrand().getId());
        dto.setProductId(voucherRequest.getProduct().getId());
        dto.setRequestDate(voucherRequest.getRequestDate());
        dto.setRequestStatus(voucherRequest.getRequestStatus().toString());
        dto.setWalletAddress(voucherRequest.getWalletAddress());


        return dto;
    }
}
