package com.nexus.vouchers.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import java.sql.Timestamp;
import com.nexus.vouchers.models.enums.VoucherStatus;



@Entity
@Table(name = "vouchers")
@Getter @Setter
@NoArgsConstructor
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private VoucherRequest voucherRequest;

    @NonNull
    @Column(name = "token_id")
    private String tokenId;

    @NonNull
    @Column(name = "issue_date")
    private Timestamp issueDate;

    @NonNull
    @Column(name = "expiry_date")
    private Timestamp expiryDate;

    @Column(name = "redemption_date")
    private Timestamp redemptionDate;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @NonNull
    @Enumerated(EnumType.STRING)
    private VoucherStatus voucherStatus;
}
