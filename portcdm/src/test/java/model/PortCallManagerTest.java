package model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arono on 2017-05-15.
 */
class PortCallManagerTest {
    PortCallManager manager;

    @BeforeAll
    static void checkConnection() {
        assertTrue(ApiConfig.isOnline(),"Server is unreachable!");
    }

    @BeforeEach
    void setUp() {
        manager = new PortCallManager();
    }

    @Test
    void refreshCalls() {
        assertTrue(manager.refreshCalls(),"no messages found");
    }

    @Test
    void getActiveCall() {
        assertNotNull(manager.getActiveCall(),"no active message");
    }

    @Test
    void getPortCall() {
        assertNotNull(manager.getPortCall(0), "no messages in list");
    }

}