package com.nexus.vouchers.models;

import lombok.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Base User model
 */


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NonNull
    protected String username;

    @NonNull
    protected String password;

    @NonNull
    protected String email;
    
    @NonNull
    protected String address;

    @Column(name = "user_type")
    @NonNull
    protected String userType; // Can be "Influencer", "Supplier", or "Brand"
    
}
