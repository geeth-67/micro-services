package com.gemtrading.trade_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class DealerServiceClient {

    private static final String DEALER_SERVICE_URL = "http://DEALER-SERVICE";

    private final RestClient restClient;

    public DealerServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public DealerResponse getDealer(Long dealerId) {
       try {
           return restClient.get()
                   .uri(DEALER_SERVICE_URL + "/api/v1/dealers/{id}", dealerId)
                   .retrieve()
                   .body(DealerResponse.class);
       }catch (RestClientException ex) {
           System.out.println("Error occurred in service to service communication" + ex.getMessage());
       }
        return null;
    }
}