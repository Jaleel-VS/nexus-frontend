package com.nexus.vouchers.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import java.sql.Timestamp;
import com.nexus.vouchers.models.enums.VoucherStatus;

@Entity
@Table(name = "voucher_requests")
@Getter @Setter
@NoArgsConstructor
public class VoucherRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "influencer_id", nullable = false)
    private Influencer influencer;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @NonNull
    @Column(name = "request_date")
    private Timestamp requestDate;

    @NonNull
    @Column(name = "voucher_status")
    @Enumerated(EnumType.STRING)
    private VoucherStatus voucherStatus;

}
