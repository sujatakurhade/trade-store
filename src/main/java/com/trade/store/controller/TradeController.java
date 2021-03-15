package com.trade.store.controller;

import com.trade.store.model.Trade;
import com.trade.store.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/")
    public String storeHome() {
        return "Welcome To Trade Store";
    }

    @PostMapping("/trade")
    public void tradeStore(@RequestBody Trade trade) {
        Trade oldTrade = tradeService.findById(trade.getTradeId());
        if (oldTrade != null) {
            tradeService.validateAndOverrideOldTrade(trade, oldTrade);
        } else {
            tradeService.createNewTrade(trade);
        }
    }
}
