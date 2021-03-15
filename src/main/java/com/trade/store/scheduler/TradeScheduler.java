package com.trade.store.scheduler;

import com.trade.store.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TradeScheduler {

    @Autowired
    private TradeService tradeService;

    @Scheduled(cron = "0 2 0 ? * *")
    public void scheduleTaskWithCronExpression() {
        System.out.println("Cron Task :: Execution Time - "+ new Date());
        tradeService.updateExpiredFlagToY();
    }
}
