package com.gemtrading.dealer_service.dto;

import com.gemtrading.dealer_service.entity.DealerTier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDealerRequest {

    @NotBlank(message = "Contact person is required")
    @Size(
            min = 2,
            max = 100,
            message = "Contact person must be between 2 and 100 characters"
    )
    private String contactPerson;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    private String email;

    @NotBlank(message = "Country is required")
    @Size(
            min = 2,
            max = 100,
            message = "Country must be between 2 and 100 characters"
    )
    private String country;

    @Size(
            max = 500,
            message = "Shipping address cannot exceed 500 characters"
    )
    private String shippingAddress;

    @NotNull(message = "Dealer tier is required")
    private DealerTier tier;
}