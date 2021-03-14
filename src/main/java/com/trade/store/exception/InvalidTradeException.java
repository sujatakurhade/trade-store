package com.trade.store.exception;

/**
 * This is custom InvalidTrade exception class which extends checked exception
 */
public class InvalidTradeException extends Exception {
    public InvalidTradeException() {
    }

    public InvalidTradeException(String message) {
        super(message);
    }

    public InvalidTradeException(String message, Throwable cause) {
        super(message, cause);
    }
}
