package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by arono on 2017-06-01.
 */
class StatusTest {
    @Test
    void getColor() {
        for (Status status :
                Status.values()) {
            assertNotNull(status.getColor(), "No color found for " + status.name());
        }
    }

}