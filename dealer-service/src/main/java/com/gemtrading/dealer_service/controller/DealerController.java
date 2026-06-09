package com.gemtrading.dealer_service.controller;

import com.gemtrading.dealer_service.entity.Dealer;
import com.gemtrading.dealer_service.entity.DealerTier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dealers")
public class DealerController {

    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealer(@PathVariable Long id) {
        return ResponseEntity.ok(Dealer.builder()
                .id(id)
                .email("test@gmail.com")
                .contactPerson("test")
                .country("Sri Lanka")
                .tier(DealerTier.BRONZE)
                .shippingAddress("12/1, colombo-02")
                .build());
    }
}
