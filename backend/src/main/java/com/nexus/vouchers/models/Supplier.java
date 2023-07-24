package com.nexus.vouchers.models;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter @Setter
@NoArgsConstructor
public class Supplier {
    @NonNull
    @Column(name = "supplier_category")
    protected String supplierCategory;

    @NonNull
    @Column(name = "supplier_description")
    protected String supplierDescription;
}
