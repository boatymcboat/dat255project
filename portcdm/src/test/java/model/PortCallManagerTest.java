package model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PortCallManagerTest {
    private PortCallManager manager;

    @BeforeAll
    static void checkConnection() {
        assertTrue(ApiConfig.isOnline(),"Server is unreachable!");
    }

    @BeforeEach
    void setUp() {
        manager = new PortCallManager();
    }

    @Test
    void refreshSummaries() {
        assertTrue(manager.refreshSummaries(), "no messages found");
    }

    @Test
    void getIds() {
        assertTrue(manager.getIds().size() > 0, "No State Definition IDs found");
    }

    @Test
    void getActiveCall() {
        assertNotNull(manager.getActiveCall(),"no active message");
    }

    @Test
    void getPortCall() {
        assertNotNull(manager.getPortCall(0), "no messages in list");
        assertNotNull(manager.getPortCall(manager.getActiveCall().getId()), "No PortCall with this ID");
    }

}