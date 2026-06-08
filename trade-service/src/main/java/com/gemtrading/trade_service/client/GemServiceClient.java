package com.gemtrading.trade_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@RequiredArgsConstructor
public class GemServiceClient {

    private final RestClient restClient;

    @Value("${gem.service.url}")
    private String gemServiceUrl;    // accessing configuration file properties

    public GemResponse getGem(Long gemId) {
        try {
            return restClient.get()
                    .uri(gemServiceUrl + "/api/v1/gems/{id}" + gemId)
                    .retrieve().body(GemResponse.class);
        }catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


}
