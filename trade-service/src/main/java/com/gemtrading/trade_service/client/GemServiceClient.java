package com.gemtrading.trade_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GemServiceClient {

    private final RestClient restClient;

    private static final Logger log =
            LoggerFactory.getLogger(GemServiceClient.class);

    private static final String GEM_SERVICE_URL = "http://GEM-TRADING";

    @CircuitBreaker(
            name = "gem-service",
            fallbackMethod = "getGemFallback"
    )
    public GemResponse getGem(Long gemId) {

        return restClient.get()
                .uri(GEM_SERVICE_URL + "/api/v1/gems/{id}", gemId)
                .retrieve()
                .body(GemResponse.class);
    }

    public GemResponse getGemFallback(Long gemId, Throwable throwable) {

        log.warn(
                "Fallback method called for getGem with id: {} due to: {}",
                gemId,
                throwable.getMessage()
        );

        GemResponse response = new GemResponse();
        response.setId(gemId);

        return response;
    }
}