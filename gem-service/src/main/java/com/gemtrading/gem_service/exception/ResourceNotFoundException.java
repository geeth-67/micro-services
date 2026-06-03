package com.gemtrading.gem_service.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource, String field){
        super(String.format("Resource Not Found - %s with field - %s", resource, field));
    }
}
