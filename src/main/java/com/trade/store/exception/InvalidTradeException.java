package com.trade.store.exception;

public class InvalidTradeException extends RuntimeException{
    public InvalidTradeException() {
    }

    public InvalidTradeException(String message) {
        super(message);
    }

    public InvalidTradeException(String message, Throwable cause) {
        super(message, cause);
    }
}
