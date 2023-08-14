package com.nexus.nexusapi.model;


import jakarta.persistence.Entity;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("BRAND")
public class Brand extends User {

    private String brandName;
    private String brandCategory;
    private String brandDescription;

    @Override
    public String getUserType() {
        return "BRAND";
    }
}