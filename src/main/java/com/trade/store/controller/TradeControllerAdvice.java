package com.trade.store.controller;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.TradeErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class TradeControllerAdvice {
    private Logger LOG = LoggerFactory.getLogger(TradeControllerAdvice.class);

    @ExceptionHandler(InvalidTradeException.class)
    public ResponseEntity<TradeErrorResponse> handleInvalidTradeException(InvalidTradeException ex, HttpServletRequest request) {
        LOG.error("Invalid Trade exception", ex);
        TradeErrorResponse tradeErrorResponse = new TradeErrorResponse();
        tradeErrorResponse.setTimestamp(LocalDateTime.now());
        tradeErrorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        tradeErrorResponse.setError("NOT ACCEPTABLE");
        tradeErrorResponse.setMessage(ex.getMessage());
        tradeErrorResponse.setPath(request.getRequestURI());
        return new ResponseEntity(tradeErrorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityViolationException.class)
    public ResponseEntity<TradeErrorResponse> handleEntityViolationException(EntityViolationException ex, HttpServletRequest request) {
        LOG.error("Entity violation exception", ex);
        TradeErrorResponse tradeErrorResponse = new TradeErrorResponse();
        tradeErrorResponse.setTimestamp(LocalDateTime.now());
        tradeErrorResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        tradeErrorResponse.setError("EXPECTATION FAILED");
        tradeErrorResponse.setMessage(ex.getMessage());
        tradeErrorResponse.setPath(request.getRequestURI());
        return new ResponseEntity(tradeErrorResponse, HttpStatus.EXPECTATION_FAILED);
    }
}

