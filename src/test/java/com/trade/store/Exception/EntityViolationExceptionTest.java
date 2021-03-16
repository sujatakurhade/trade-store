package com.trade.store.Exception;

import com.trade.store.exception.EntityViolationException;
import org.junit.jupiter.api.Test;

public class EntityViolationExceptionTest {

    @Test
    public void testConstructorNoArgs() {
        EntityViolationException entityViolationException = new EntityViolationException();
    }

    @Test
    public void testConstructorWithArgs() {
        EntityViolationException entityViolationExceptionWithArgs = new EntityViolationException("EntityViolationException");
    }
}
