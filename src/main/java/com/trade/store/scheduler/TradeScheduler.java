package com.trade.store.scheduler;

import com.trade.store.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class TradeScheduler {

    private Logger LOG = LoggerFactory.getLogger(TradeScheduler.class);

    @Autowired
    private TradeService tradeService;

    /**
     * @PostConstruct execute after all bean properties are initialised,
     * in this case, the application can ensure that the expired flag is updated correctly on startup,
     * because if the application misses the Cron period for some reason, such as a malfunction, this is the contingency plan to update it.
     */
    @PostConstruct
    public void onApplicationStartUp() {
        LOG.info("Application StartUp :: Execution Time - " + new Date());
        this.updateExpiredFlag();
    }

    /**
     * This cron task which execute every day midnight 00.02 AM to ensure expiry flag should be updated based on the maturity date
     */
    @Scheduled(cron = "0 2 0 ? * *")
    public void scheduleTask() {
        LOG.info("Cron Task :: Execution Time - " + new Date());
        this.updateExpiredFlag();
    }

    private void updateExpiredFlag() {
        tradeService.updateExpiredFlagToY();
    }
}
