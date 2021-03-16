package com.trade.store.exception;

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
