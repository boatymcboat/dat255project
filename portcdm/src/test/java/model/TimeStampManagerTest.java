package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeStampManagerTest {
    private TimeStampManager tsmanager;
    private StatementReader reader;
    private PortCallManager pcmanager;

    @BeforeEach
    void setUp() {
        pcmanager = new PortCallManager();
        reader = new StatementReader(pcmanager.getPortCall(0));
        tsmanager = new TimeStampManager(reader.getAllStatements());
    }

    @Test
    void checkStatements() {
        System.out.println(tsmanager.checkStatements("Arrival_Vessel_TrafficArea"));
    }

}