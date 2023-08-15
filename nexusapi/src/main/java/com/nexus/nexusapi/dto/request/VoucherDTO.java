package com.nexus.nexusapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {
    private String brandID;
    private String influencerID;
    private String supplierID;
    private String productID;
    private boolean redeemed;
    private Long expiryDate;
    private String voucherPin;
}
