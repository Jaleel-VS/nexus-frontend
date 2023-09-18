package com.nexus.nexusapi.dto.response;

import com.nexus.nexusapi.model.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SupplierDTO extends UserDTO {
    private String supplierName;
    private String supplierCategory;
    private String supplierDescription;

    public static SupplierDTO fromEntity(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setUsername(supplier.getUsername());
        dto.setEmail(supplier.getEmail());
        dto.setAddress(supplier.getAddress());
        dto.setMetamaskAddress(supplier.getMetamaskAddress());
        dto.setProfilePictureUrl(supplier.getProfilePictureUrl());
        dto.setUserType(supplier.getUserType());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setSupplierCategory(supplier.getSupplierCategory());
        dto.setSupplierDescription(supplier.getSupplierDescription());
        return dto;
    }
}
