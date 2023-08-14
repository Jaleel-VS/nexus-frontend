package com.nexus.nexusapi.dto.response;

import java.time.LocalDate;

import com.nexus.nexusapi.model.VoucherRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherRequestResponseDTO {
    private Long id;
    private Long influencerId;
    private Long brandId;
    private Long productId;
    private LocalDate requestDate;
    private String requestStatus;
    private String walletAddress;
}
