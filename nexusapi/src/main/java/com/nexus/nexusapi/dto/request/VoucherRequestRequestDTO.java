package com.nexus.nexusapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherRequestRequestDTO {
    private Long influencerId;
    private Long brandId;
    private Long productId;
    private String walletAddress;
    
}
