package com.trade.store.exception;

/**
 * This is custom EntityViolation exception class which extends checked exception
 */
public class EntityViolationException extends Exception {
    public EntityViolationException() {
    }

    public EntityViolationException(String message) {
        super(message);
    }

    public EntityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
