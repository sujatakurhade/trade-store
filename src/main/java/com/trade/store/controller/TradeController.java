package com.trade.store.controller;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.Trade;
import com.trade.store.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Trade controller has request mapping
 */
@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * This request mapping accept trade in request and validate and update the store
     *
     * @param trade
     * @return Boolean
     * @throws InvalidTradeException
     * @throws EntityViolationException
     */
    @PostMapping("/trade")
    public Boolean tradeStore(@RequestBody Trade trade) throws InvalidTradeException, EntityViolationException {
        return tradeService.validateAndUpdateStore(trade);
    }

    // NOTE: This Mapping created for testing to view all trades records from store
    @GetMapping("/trade")
    public List<Trade> getAllTrades() {
        return tradeService.findAllTrade();
    }

    // NOTE: This Mapping created for testing purpose
    @GetMapping("/trigger-scheduler")
    public void getUpdateExpiredFlagToY() {
        tradeService.updateExpiredFlagToY();
    }
}
