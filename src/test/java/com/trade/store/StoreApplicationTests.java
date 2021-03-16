package com.trade.store;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {

    //  write a simple sanity check test that will fail if the application context cannot start.
    @Test
    void contextLoads() {
    }

    @Test
    public void StoreApplication() {
        StoreApplication.main(new String[]{});
    }

}
