package com.nexus.nexusapi.model;

import jakarta.persistence.Entity;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("Influencer")
public class Influencer extends User {

    private String firstName;
    private String lastName;
    private String instagramHandle;

    @Override
    public String getUserType() {
        return "Influencer";
    }

    
}