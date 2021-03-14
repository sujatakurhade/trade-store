package com.trade.store.model;

public class ExceptionTradeResponse {
    private String error;

    public ExceptionTradeResponse() {
    }

    public ExceptionTradeResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
