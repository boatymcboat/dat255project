package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arono on 2017-05-15.
 */
class StatementReaderTest {
    StatementReader reader;
    PortCallManager manager;

    @BeforeEach
    void setUp() {
        manager = new PortCallManager();
        reader = new StatementReader(manager.getPortCall(0));
    }

    @Test
    void setActiveCall() {
        assertTrue(reader.setActiveCall(manager.getPortCall(1)));
    }

    @Test
    void getStatement() { // Kvar att fixa xd
        assertNotNull(reader.getStatement("Arrival_Vessel_TrafficArea"),"no statements found");
    }

}