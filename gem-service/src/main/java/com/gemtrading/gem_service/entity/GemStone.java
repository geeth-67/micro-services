package com.gemtrading.gem_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gemstones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GemStone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String gemCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GemType type;

    @ManyToMany
    @JoinTable(
            name = "gemstone_tags",
            joinColumns = @JoinColumn(name = "gemstone_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private BigDecimal caratWeight; //we use bigDecimal to avoid rounding issues with double,
    //big decimal gives precision to our decimal values

    @OneToOne(mappedBy = "gemStone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Certificate certificate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GemOrigin origin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GemTreatment treatment;

    @Column(nullable = false)
    private BigDecimal pricePerCarat;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private boolean certified = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
