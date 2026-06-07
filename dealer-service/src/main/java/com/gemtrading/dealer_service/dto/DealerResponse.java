package com.gemtrading.dealer_service.dto;

import com.gemtrading.dealer_service.entity.DealerTier;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealerResponse {


    private Long id;
    private String companyName;
    private String contactPerson;
    private String email;
    private String country;
    private String shippingAddress;
    private DealerTier tier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
