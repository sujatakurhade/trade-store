package com.trade.store.service;

import com.trade.store.model.Trade;

public interface TradeService {

    void validateAndOverrideOldTrade(Trade trade,Trade oldTrade);

    void createNewTrade(Trade trade);

    Trade findById(String tradeId);

    void updateExpiredFlagToY();
}
