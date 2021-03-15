package com.trade.store.service.impl;

import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.Trade;
import com.trade.store.repository.TradeRepository;
import com.trade.store.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public void validateAndOverrideOldTrade(Trade trade, Trade oldTrade) {
        if (validMaturityDate(trade)) {
            if (validateVersion(trade, oldTrade)) {
                trade.setCreatedDate(LocalDate.now());
                tradeRepository.save(trade);
            } else {
                throw new InvalidTradeException("Invalid version for trade " + trade.getTradeId());
            }
        } else {
            throw new InvalidTradeException("Invalid Maturity date for trade " + trade.getTradeId());
        }
    }

    @Override
    public void createNewTrade(Trade trade) {
        if (validMaturityDate(trade)) {
            trade.setCreatedDate(LocalDate.now());
            tradeRepository.save(trade);
        } else {
            throw new InvalidTradeException("Invalid Maturity date for trade " + trade.getTradeId());
        }

    }

    @Override
    public Trade findById(String tradeId) {
        return tradeRepository.findByTradeId(tradeId);
    }

    @Override
    public void updateExpiredFlagToY() {
        tradeRepository.findAll().forEach(trade -> {
            if (!validMaturityDate(trade)) {
                trade.setExpiredFlag("Y");
                tradeRepository.save(trade);
            }
        });
    }


    boolean validMaturityDate(Trade trade) {
        if (trade.getMaturityDate().compareTo(LocalDate.now()) > 0) {
            System.out.println("LocalDate" + LocalDate.now());
            return true;
        } else {
            return false;
        }
    }

    boolean validateVersion(Trade trade, Trade existingTradeInDb) {
        return trade.getVersion() >= existingTradeInDb.getVersion();
    }
}
