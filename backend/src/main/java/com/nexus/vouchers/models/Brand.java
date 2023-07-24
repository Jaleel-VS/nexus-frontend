package com.nexus.vouchers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "brand")
@Getter @Setter
@NoArgsConstructor
public class Brand extends User {   
    // brand category and description
    @NonNull
    private String category;

    @NonNull
    private String description;
}
