package com.gemtrading.gem_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException ex
            , HttpServletRequest request) {

        ApiErrorResponse errorResponse =  ApiErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("Resource Not found")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateResource(DuplicateResourceException ex
            , HttpServletRequest request) {

        ApiErrorResponse errorResponse =  ApiErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error("Duplicate Resource")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);

    }

    @ExceptionHandler(GemNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleGemNotFound(DuplicateResourceException ex
            , HttpServletRequest request) {

        ApiErrorResponse errorResponse =  ApiErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }
}
