package com.nexus.nexusapi.dto.response;

import com.nexus.nexusapi.model.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BrandDTO extends UserDTO {
    private String brandName;
    private String brandCategory;
    private String brandDescription;

    // Getters and setters

    public static BrandDTO fromEntity(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setUsername(brand.getUsername());
        dto.setEmail(brand.getEmail());
        dto.setAddress(brand.getAddress());
        dto.setUserType(brand.getUserType());
        dto.setBrandName(brand.getBrandName());
        dto.setBrandCategory(brand.getBrandCategory());
        dto.setBrandDescription(brand.getBrandDescription());
        return dto;
    }
}
