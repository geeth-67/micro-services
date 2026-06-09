package com.gemtrading.trade_service.client;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GemResponse {

    private Long id;
    private String gemCode;
    private String type;
    private String color;
    private BigDecimal caratWeight;
    private String origin;
    private String treatment;
    private Double pricePerCarat;
    private Integer stockQuantity;
    private Boolean certified;
    private String description;
    private Boolean active;
}
