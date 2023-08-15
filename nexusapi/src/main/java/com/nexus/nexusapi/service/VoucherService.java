package com.nexus.nexusapi.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexusapi.dto.request.VoucherDTO;
import com.nexus.nexusapi.exceptions.NotFoundException;
import com.nexus.nexusapi.model.Brand;
import com.nexus.nexusapi.model.Influencer;
import com.nexus.nexusapi.model.Product;
import com.nexus.nexusapi.model.Supplier;
import com.nexus.nexusapi.model.Voucher;
import com.nexus.nexusapi.repository.BrandRepository;
import com.nexus.nexusapi.repository.InfluencerRepository;
import com.nexus.nexusapi.repository.ProductRepository;
import com.nexus.nexusapi.repository.SupplierRepository;
import com.nexus.nexusapi.repository.VoucherRepository;

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

    // create voucher
    public VoucherDTO createVoucher(VoucherDTO voucherDTO) {
        Voucher voucher = new Voucher();
        
        Brand brand = brandRepository.findById(Long.parseLong(voucherDTO.getBrandID()))
                .orElseThrow(() -> new NotFoundException("Brand not found"));
        Influencer influencer = influencerRepository.findById(Long.parseLong(voucherDTO.getInfluencerID()))
                .orElseThrow(() -> new NotFoundException("Influencer not found"));

        Supplier supplier = supplierRepository.findById(Long.parseLong(voucherDTO.getSupplierID()))
                .orElseThrow(() -> new NotFoundException("Supplier not found"));

        Product product = productRepository.findById(Long.parseLong(voucherDTO.getProductID()))
                .orElseThrow(() -> new NotFoundException("Product not found"));

        voucher.setBrand(brand);
        voucher.setInfluencer(influencer);
        voucher.setSupplier(supplier);
        voucher.setProduct(product);

        voucher.setCreatedDate(java.time.LocalDate.now());
        
        Long expiryDate = voucherDTO.getExpiryDate();
        if (expiryDate != null) {
            voucher.setExpiryDate(LocalDate.ofEpochDay(expiryDate));
        } else {
            voucher.setExpiryDate(LocalDate.ofEpochDay(0));
        }

        voucher.setVoucherPin(voucherDTO.getVoucherPin());

        voucher.setRedeemed(false);

        String blockchainUrl = mintVoucher(influencer, brand, supplier, product, voucher.getExpiryDate());

        voucher.setBlockchainUrl(blockchainUrl);
        
        voucherRepository.save(voucher);
        return voucherDTO;
    }


    private String mintVoucher(Influencer influencer, Brand brand, Supplier supplier, Product product, LocalDate expiryDate) {
        return "";
    }
    
}
