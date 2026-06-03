package com.gemtrading.gem_service.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;
}
