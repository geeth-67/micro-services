package com.gemtrading.gem_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String certificateNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gemstone_id", nullable = false, unique = true)
    private GemStone gemStone;

    @Column(nullable = false)
    private String issuedBy;

    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(length = 1000)
    private String remarks;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
