package com.gemtrading.gem_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagRequest {

    @NotBlank(message = "Tag name is required")
    @Size(min = 2, max = 30)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 limit")
    private String description;
}
