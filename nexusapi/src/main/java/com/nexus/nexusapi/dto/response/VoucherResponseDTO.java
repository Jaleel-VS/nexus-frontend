package com.nexus.nexusapi.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherResponseDTO {
    private Long id;
    private Long influencerId;
    private Long brandId;
    private Long productId;
    // private Long supplierId;
    private LocalDate creationDate;
    private LocalDate expiryDate;
    private boolean redeemed;   
    private String blockchainURL; 
}
