package com.trade.store.exception;

/**
 * This is custom EntityViolation exception class which extends checked exception
 */
public class EntityViolationException extends Exception {
    private String error;
    public EntityViolationException() {
    }

    public EntityViolationException(String error) {
        super(error);
    }
}
