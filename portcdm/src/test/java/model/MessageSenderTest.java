package model;

import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.messaging.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arono on 2017-05-15.
 */
class MessageSenderTest {

    @BeforeAll
    static void checkConnection() {
        assertTrue(ApiConfig.isOnline(),"Server is unreachable!");
    }

    @Test
    void createMessage() {
        MessageSender sender = new MessageSender();
        PortCallMessage pcm = sender.createMessage();
        assertNotNull(pcm,"Port Call Message is Null");
    }

    @Test
    void sendMessage() {
        MessageSender sender = new MessageSender();
        PortCallMessage pcm = sender.createMessage();
        assertTrue(sender.sendMessage(pcm), "Could not send PCM");
    }

    @Test
    void sendLocationState() {
        MessageSender sender = new MessageSender();
        PortCallManager manager = new PortCallManager();
        sender.sendLocationState(manager.getActiveCall(), LocationTimeSequence.ARRIVAL_TO,
                LogicalLocation.ANCHORING_AREA, LogicalLocation.BERTH,
                ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT), TimeType.ESTIMATED);
    }

    @Test
    void sendServiceState() {
        //TODO: Load FXml here so that there are no null pointer exceptions.
        /*MessageSender sender = new MessageSender();
        PortCallManager manager = new PortCallManager();
        sender.sendServiceState(manager.getPortCall(1), ServiceObject.CARGO_OPERATION, ServiceTimeSequence.COMMENCED,
                LogicalLocation.ANCHORING_AREA ,ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT),
                TimeType.ESTIMATED);
                */
    }
}