package com.trade.store.service.impl;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.Trade;
import com.trade.store.repository.TradeRepository;
import com.trade.store.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TradeServiceImpl implements TradeService {
    private Logger LOG = LoggerFactory.getLogger(TradeServiceImpl.class);

    private static final String Y = "Y";

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public Boolean validateAndUpdateStore(Trade trade) throws InvalidTradeException, EntityViolationException {

        //  Trade id is not present then create it (Maturity date validation)
        //  Trade id is present then check version like....
        //    if it's same then  override the trade  (Maturity date validation)
        //    lower version is received throw exception
        //    if it's greater version received add it (Maturity date validation)
        List<Trade> oldTradeList = tradeRepository.findByTradeId(trade.getTradeId());
        if (!oldTradeList.isEmpty()) {
            this.validateVersionAndUpdate(trade, oldTradeList);
        } else {
            this.createNewTrade(trade);
        }
        return Boolean.TRUE;
    }

    public void createNewTrade(Trade trade) throws InvalidTradeException {
        if (this.validMaturityDate(trade)) {
            trade.setCreatedDate(LocalDate.now());
            tradeRepository.save(trade);
        }
    }

    private boolean validMaturityDate(Trade trade) throws InvalidTradeException {
        if (trade.getMaturityDate().isBefore(LocalDate.now())) {
            throw new InvalidTradeException("Invalid Maturity date for trade " + trade.getTradeId());
        }
        return Boolean.TRUE;
    }

    private void validateVersionAndUpdate(Trade trade, List<Trade> oldTradeList) throws InvalidTradeException, EntityViolationException {
        Map<Integer, Trade> oldTradeMap = oldTradeList.stream().collect(Collectors.toMap(Trade::getVersion, oldTrade -> oldTrade));
        if (Optional.ofNullable(oldTradeMap.get(trade.getVersion())).isPresent()) {
            // Override trade
            updateExistingTrade(trade);
        } else if (trade.getVersion() < Collections.min(oldTradeMap.keySet())) {
            throw new InvalidTradeException("Invalid version for trade " + trade.getTradeId());
        } else {
            //Add new trade
            this.createNewTrade(trade);
        }
    }

    private void updateExistingTrade(Trade trade) throws EntityViolationException, InvalidTradeException {
        if (this.validMaturityDate(trade)) {
            int status = tradeRepository.updateTradeByTradeIdAndVersion(trade.getTradeId(), trade.getVersion(), trade.getCounterPartyId(), trade.getBookId(),
                    trade.getMaturityDate(), trade.getExpiredFlag());
            if (status <= 0) {
                throw new EntityViolationException("Error while updating trade " + trade.getTradeId() + ", please try after sometime ");
            }
        }
    }

    @Override
    public void updateExpiredFlagToY() {
        tradeRepository.findAll().forEach(trade -> {
            if (trade.getMaturityDate().isBefore(LocalDate.now())) {
                int status = tradeRepository.updateTradeByTradeIdAndVersion(trade.getTradeId(), trade.getVersion(), Y);
                if (status <= 0) {
                    LOG.warn("Error while updating expiry flag for trade {} and version {}", trade.getTradeId(), trade.getVersion());
                }
            }
        });
    }

}