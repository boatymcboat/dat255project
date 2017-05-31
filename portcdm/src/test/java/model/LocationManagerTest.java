package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocationManagerTest {
    @Test
    void getAnchoringAreas() {
        assertTrue(LocationManager.getAnchoringAreas().length > 0, "No Anchoring Areas found");
    }

    @Test
    void getBerths() {
        assertTrue(LocationManager.getBerths().length > 0, "No Berths found");
    }

    @Test
    void geteTugZones() {
        assertTrue(LocationManager.geteTugZones().length > 0, "No ETug Zones found");
    }

    @Test
    void getLocs() {
        assertTrue(LocationManager.getLocs().length > 0, "No LOCs found");
    }

    @Test
    void getPilotBAs() {
        assertTrue(LocationManager.getPilotBAs().length > 0, "No Pilot Boarding Areas found");
    }

    @Test
    void getTrafficAreas() {
        assertTrue(LocationManager.getTrafficAreas().length > 0, "No Traffic Areas found");
    }

    @Test
    void getTugZones() {
        assertTrue(LocationManager.geteTugZones().length > 0, "No Tug Zones found");
    }

}