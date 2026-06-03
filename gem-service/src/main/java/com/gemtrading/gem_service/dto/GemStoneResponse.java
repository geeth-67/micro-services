package com.gemtrading.gem_service.dto;



import com.gemtrading.gem_service.entity.GemOrigin;
import com.gemtrading.gem_service.entity.GemTreatment;
import com.gemtrading.gem_service.entity.GemType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GemStoneResponse {

    private Long id;
    private String gemCode;
    private GemType type;
    private String color;
    private BigDecimal caratWeight;
    private GemOrigin origin;
    private GemTreatment treatment;
    private BigDecimal pricePerCarat;
    private Integer stockQuantity;
    private boolean certified;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
