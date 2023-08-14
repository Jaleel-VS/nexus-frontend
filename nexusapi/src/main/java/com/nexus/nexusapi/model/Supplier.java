package com.nexus.nexusapi.model;


import jakarta.persistence.Entity;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("SUPPLIER")
public class Supplier extends User {

    private String supplierName;
    private String supplierCategory;
    private String supplierDescription;

    @Override
    public String getUserType() {
        return "SUPPLIER";
    }
}