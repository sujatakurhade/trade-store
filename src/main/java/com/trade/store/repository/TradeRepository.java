package com.trade.store.repository;

import com.trade.store.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TradeRepository extends JpaRepository<Trade,String> {
    Trade findByTradeId(String tradeId);
}
