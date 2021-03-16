package com.trade.store.exception;

public class InvalidTradeException extends Exception{
    public InvalidTradeException() {
    }

    public InvalidTradeException(String message) {
        super(message);
    }

    public InvalidTradeException(String message, Throwable cause) {
        super(message, cause);
    }
}
