package com.nexus.nexusapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexusapi.dto.response.VoucherResponseDTO;
import com.nexus.nexusapi.service.VoucherService;
import com.nexus.nexusapi.dto.request.VoucherDTO;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    // create voucher
    @PostMapping
    public ResponseEntity<VoucherResponseDTO> createVoucher(@RequestBody VoucherDTO voucherDTO) {
        System.out.println("VoucherDTO: " + voucherDTO.toString());

        return ResponseEntity.ok(voucherService.createVoucher(voucherDTO));
    }

    // get voucher by id
    @GetMapping("/{id}")
    public ResponseEntity<VoucherResponseDTO> getVoucherById(@PathVariable Long id) {
        return ResponseEntity.ok(voucherService.getVoucherById(id));
    }

    // get all vouchers
    @GetMapping
    public ResponseEntity<Iterable<VoucherResponseDTO>> getAllVouchers() {
        return ResponseEntity.ok(voucherService.getAllVouchers());
    }

    // update voucher status
    @PutMapping("/{id}/{status}")
    public ResponseEntity<VoucherResponseDTO> updateVoucherStatus(@PathVariable Long id, @PathVariable boolean status) {
        return ResponseEntity.ok(voucherService.updateVoucherStatus(id, status));
    }

    // check if redeemed
    @GetMapping("/{id}/redeemed")
    public ResponseEntity<Boolean> isRedeemed(@PathVariable Long id) {
        return ResponseEntity.ok(voucherService.isRedeemed(id));
    }

    // redeem voucher
    @GetMapping("/redeem/{voucherQRCodeString}/supplier/{supplierId}")
    public ResponseEntity<Boolean> redeemVoucher(@PathVariable String voucherQRCodeString, @PathVariable Long supplierId) {
        return ResponseEntity.ok(voucherService.redeemVoucher(voucherQRCodeString, supplierId));
    }

    // get voucher by voucherRequest id
    @GetMapping("/voucherRequest/{id}")
    public ResponseEntity<VoucherResponseDTO> getVoucherByVoucherRequestId(@PathVariable Long id) {
        return ResponseEntity.ok(voucherService.getVoucherByRequestId(id));
    }

}
