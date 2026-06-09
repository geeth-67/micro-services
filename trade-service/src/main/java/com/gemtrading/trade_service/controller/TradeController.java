package com.gemtrading.trade_service.controller;

import com.gemtrading.trade_service.client.GemResponse;
import com.gemtrading.trade_service.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trade")
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/gem/{id}")
    public GemResponse gemResponse(@PathVariable Long id) {
        return tradeService.getGem(id);
    }
}
