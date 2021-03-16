package com.trade.store.controller;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.TradeErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(SpringExtension.class)
public class TradeAdviceControllerTest {

    @InjectMocks
    private TradeControllerAdvice tradeControllerAdvice;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Test
    public void testInvalidTradeException() {
        ResponseEntity<TradeErrorResponse> tradeErrorResponseResponseEntity = tradeControllerAdvice.handleInvalidTradeException(new InvalidTradeException("Test"), httpServletRequest);
        assertNotNull(tradeErrorResponseResponseEntity);
        assertEquals(406, tradeErrorResponseResponseEntity.getStatusCodeValue());
    }

    @Test
    public void testEntityViolationException() {
        ResponseEntity<TradeErrorResponse> tradeErrorResponseResponseEntity = tradeControllerAdvice.handleEntityViolationException(new EntityViolationException("Test"), httpServletRequest);
        assertNotNull(tradeErrorResponseResponseEntity);
        assertEquals(417, tradeErrorResponseResponseEntity.getStatusCodeValue());
    }
}
