package com.trade.store.scheduler;

import com.trade.store.service.TradeService;
import com.trade.store.service.impl.TradeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TradeScheduler {

    private Logger LOG = LoggerFactory.getLogger(TradeServiceImpl.class);


    @Autowired
    private TradeService tradeService;

    @Scheduled(cron = "0 2 0 ? * *")
    public void scheduleTaskWithCronExpression() {
        LOG.info("Cron Task :: Execution Time - "+ new Date());
        tradeService.updateExpiredFlagToY();
    }
}
