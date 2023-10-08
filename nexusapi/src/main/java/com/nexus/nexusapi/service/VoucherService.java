package com.nexus.nexusapi.service;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexusapi.dto.request.VoucherDTO;
import com.nexus.nexusapi.dto.response.VoucherResponseDTO;
import com.nexus.nexusapi.exceptions.NotFoundException;
import com.nexus.nexusapi.model.Brand;
import com.nexus.nexusapi.model.Influencer;
import com.nexus.nexusapi.model.Product;
import com.nexus.nexusapi.model.Voucher;
import com.nexus.nexusapi.model.VoucherRequest;
import com.nexus.nexusapi.repository.BrandRepository;
import com.nexus.nexusapi.repository.InfluencerRepository;
import com.nexus.nexusapi.repository.ProductRepository;
import com.nexus.nexusapi.repository.SupplierRepository;
import com.nexus.nexusapi.repository.VoucherRepository;
import com.nexus.nexusapi.repository.VoucherRequestRepository;
import com.nexus.nexusapi.web3.Web3Manager;

@Service
public class VoucherService {

        @Autowired
        private VoucherRepository voucherRepository;

        @Autowired
        private BrandRepository brandRepository;

        @Autowired
        private InfluencerRepository influencerRepository;

        @Autowired
        private SupplierRepository supplierRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private VoucherRequestRepository voucherRequestRepository;

        @Autowired
        private Web3Manager web3Manager;

        // create voucher
        public VoucherResponseDTO createVoucher(VoucherDTO voucherDTO) {
                Voucher voucher = new Voucher();

                VoucherRequest voucherRequest = voucherRequestRepository
                                .findById(Long.parseLong(voucherDTO.getVoucherRequestID()))
                                .orElseThrow(() -> new NotFoundException("Voucher Request not found"));

                Brand brand = brandRepository.findById(voucherRequest.getBrand().getId())
                                .orElseThrow(() -> new NotFoundException("Brand not found"));

                Influencer influencer = influencerRepository.findById(voucherRequest.getInfluencer().getId())
                                .orElseThrow(() -> new NotFoundException("Influencer not found"));

                Product product = productRepository.findById(voucherRequest.getProduct().getId())
                                .orElseThrow(() -> new NotFoundException("Product not found"));

                voucher.setVoucherRequest(voucherRequest);

                voucher.setBrand(brand);
                voucher.setInfluencer(influencer);
                voucher.setProduct(product);

                voucher.setCreatedDate(java.time.LocalDate.now());

                Long expiryDateInSeconds = voucherDTO.getExpiryDate();
                if (expiryDateInSeconds != null) {
                        long daysSinceEpoch = expiryDateInSeconds / (24 * 60 * 60); // Convert seconds to days
                        voucher.setExpiryDate(LocalDate.ofEpochDay(daysSinceEpoch));
                } else {
                        voucher.setExpiryDate(LocalDate.ofEpochDay(0));
                }

                String recipientAddress = influencer.getMetamaskAddress();

                voucher.setRedeemed(false);
                String blockchainUrl = mintVoucher(influencer, brand, product,
                                voucher.getExpiryDate(), recipientAddress);

                voucher.setBlockchainUrl(blockchainUrl);

                voucherRepository.save(voucher);

                VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                voucherResponseDTO.setId(voucher.getId());
                voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                voucherResponseDTO.setProductId(voucher.getProduct().getId());
                voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                voucherResponseDTO.setBlockchainURL(voucher.getBlockchainUrl());
                return voucherResponseDTO;
        }

        private String mintVoucher(Influencer influencer, Brand brand, Product product,
                        LocalDate expiryDate, String requestAddress) {
                String influencerID = influencer.getId().toString();
                String brandID = brand.getId().toString();
                String productID = product.getId().toString();
                // use streams, with a map function to convert the list of suppliers to a string, and have a comma
                String supplierIDs = product.getSuppliers().stream().map(supplier -> supplier.getId().toString())
                                .reduce("", (a, b) -> a + "," + b);
                Long expiryDateLong = expiryDate.toEpochDay();

                String blockchainUrl = web3Manager.mintVoucher(influencerID, brandID, supplierIDs,
                                productID,
                                expiryDateLong, requestAddress);

                // System.out.println(supplierIDs);

                return blockchainUrl;
        }

        // get voucher by id
        public VoucherResponseDTO getVoucherById(Long id) {
                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));
                VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                voucherResponseDTO.setProductId(voucher.getProduct().getId());
                voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                return voucherResponseDTO;
        }

        // update voucher status
        public VoucherResponseDTO updateVoucherStatus(Long id, boolean status) {
                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));
                voucher.setRedeemed(status);
                voucherRepository.save(voucher);
                VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                voucherResponseDTO.setProductId(voucher.getProduct().getId());
                voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                return voucherResponseDTO;
        }

        // check if voucher is redeemed
        public boolean isRedeemed(Long id) {
                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));
                return voucher.isRedeemed();
        }

    public Boolean redeemVoucher(Long id, Long supplierId) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Voucher not found"));

        String supplierAddress = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier not found"))
                .getMetamaskAddress();

        String success = web3Manager.redeemVoucher(BigInteger.ONE, supplierAddress);

        if (success.equals("true")) {
            voucher.setRedeemed(true);
            voucherRepository.save(voucher);
        }

        return true;
    }
}
