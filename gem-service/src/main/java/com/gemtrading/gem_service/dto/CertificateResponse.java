package com.gemtrading.gem_service.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateResponse {

    private Long id;
    private String certificateNumber;
    private Long gemId;
    private String gemCode;
    private String issuedBy;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private String remarks;
    private LocalDateTime createdAt;
}
