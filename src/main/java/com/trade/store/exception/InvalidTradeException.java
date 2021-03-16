package com.trade.store.exception;

/**
 * This is custom InvalidTrade exception class which extends checked exception
 */
public class InvalidTradeException extends Exception {
    private String error;

    public InvalidTradeException() {
    }

    public InvalidTradeException(String error) {
        super(error);
    }
}
