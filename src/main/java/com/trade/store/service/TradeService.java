package com.trade.store.service;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.Trade;

public interface TradeService {

    /**
     * This method validate trade based on the business constraints and update the trade store accordingly.
     *
     * @param trade
     * @return Boolean
     * @throws InvalidTradeException
     * @throws EntityViolationException
     */
    Boolean validateAndUpdateStore(Trade trade) throws InvalidTradeException, EntityViolationException;


    void updateExpiredFlagToY();
}
