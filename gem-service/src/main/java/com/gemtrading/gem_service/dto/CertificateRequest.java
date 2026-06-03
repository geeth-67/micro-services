package com.gemtrading.gem_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateRequest {

    @NotBlank(message = "Certificate number is required")   //""
    private String certificateNumber;

    @NotNull(message = "Gem Id is required")
    private Long gemId;

    @NotBlank(message = "Issued by is required")
    private String issuedBy;

    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    private String remarks;
}
