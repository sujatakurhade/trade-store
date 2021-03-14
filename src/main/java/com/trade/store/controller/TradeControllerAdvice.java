package com.trade.store.controller;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.ExceptionTradeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TradeControllerAdvice {
    @ExceptionHandler(InvalidTradeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionTradeResponse handleInvalidTradeException(InvalidTradeException se) {
        ExceptionTradeResponse response = new ExceptionTradeResponse(se.getMessage());
        return response;
    }

    @ExceptionHandler(EntityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionTradeResponse handleEntityViolationException(InvalidTradeException se) {
        ExceptionTradeResponse response = new ExceptionTradeResponse(se.getMessage());
        return response;
    }
}
