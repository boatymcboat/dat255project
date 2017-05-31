package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiConfigTest {
    @Test
    void isOnline() {
        assertTrue(ApiConfig.isOnline());
    }

}