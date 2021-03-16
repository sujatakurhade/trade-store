package com.trade.store.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;

import org.awaitility.Duration;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestPropertySource(properties = "cronExpression=5 0 0 ? * *")
public class TradeSchedulerTest {

    @SpyBean
    TradeScheduler tradeScheduler;

   /* @Test
    public void scheduleTask() {
        await().atMost(Duration.FIVE_SECONDS)
                .untilAsserted(() -> {
                    verify(tradeScheduler, atLeast(1)).scheduleTask();
                });
    }*/
}
