package com.trade.store.controller;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.Trade;
import com.trade.store.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/trade")
    public Boolean tradeStore(@RequestBody Trade trade) throws InvalidTradeException, EntityViolationException {
        return tradeService.validateAndUpdateStore(trade);
}

    @GetMapping("/trade")
    public List<Trade> getAllTrades(){
        return tradeService.findAllTrade();
    }

    @GetMapping("/trigger")
    public void getUpdateExpiredFlagToY() {
        tradeService.updateExpiredFlagToY();
    }
}
