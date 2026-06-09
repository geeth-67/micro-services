package com.gemtrading.trade_service.service;

import com.gemtrading.trade_service.client.DealerServiceClient;
import com.gemtrading.trade_service.client.GemResponse;
import com.gemtrading.trade_service.client.GemServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final GemServiceClient gemServiceClient;
    private final DealerServiceClient dealerServiceClient;

    public GemResponse getGem(Long id) {
        return gemServiceClient.getGem(id);
    }
}
