package com.nexus.nexusapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {
    private String voucherRequestID;
    private String supplierID;
    private Long expiryDate;

}
