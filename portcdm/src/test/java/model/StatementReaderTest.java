package model;

import eu.portcdm.dto.PortCall;
import eu.portcdm.dto.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arono on 2017-05-15.
 */
class StatementReaderTest {

    StatementReader reader;
    PortCallManager manager;

    @BeforeAll
    static void checkConnection() {
        assertTrue(ApiConfig.isOnline(),"Server is unreachable!");
    }

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
    void getStatement() { // TODO: Ej hållbart, kan vara bra att koda om
        assertNotNull(reader.getStatement("Arrival_Vessel_Berth"),"no statements found");
    }

    @Test
    void getAllStatements() {
        HashMap<String, List<Statement>> statements = reader.getAllStatements(manager.getPortCall(0));
        assertFalse(statements.isEmpty(),"No statements found");
    }

}