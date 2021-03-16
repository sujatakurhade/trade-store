package com.trade.store.Exception;

import com.trade.store.exception.InvalidTradeException;
import org.junit.jupiter.api.Test;

public class InvalidTradeExceptionTest {

    @Test
    public void testConstructorNoArgs() {
        InvalidTradeException invalidTradeException = new InvalidTradeException();
    }

    @Test
    public void testConstructorWithArgs() {
        InvalidTradeException invalidTradeExceptionWithArgs = new InvalidTradeException("Invalid Trade Exception");
    }
}
