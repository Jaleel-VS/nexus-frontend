package com.nexus.vouchers.models;

import lombok.NoArgsConstructor;
import lombok.Setter;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "influencers")
@Getter @Setter
@NoArgsConstructor
public class Influencer extends User {
    @NonNull
    @Column(name = "instagram_handle")
    protected String instagramHandle;
}
