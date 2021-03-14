package com.trade.store.controller;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.ExceptionTradeResponse;
import com.trade.store.service.impl.TradeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TradeControllerAdvice {
    private Logger LOG = LoggerFactory.getLogger(TradeControllerAdvice.class);

    @ExceptionHandler(InvalidTradeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ExceptionTradeResponse handleInvalidTradeException(InvalidTradeException ex) {
        LOG.error("Invalid Trade exception", ex);
        return new ExceptionTradeResponse(ex.getMessage());
    }

    @ExceptionHandler(EntityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ExceptionTradeResponse handleEntityViolationException(InvalidTradeException ex) {
        LOG.error("Entity violation exception", ex);
        return new ExceptionTradeResponse(ex.getMessage());
    }
}
