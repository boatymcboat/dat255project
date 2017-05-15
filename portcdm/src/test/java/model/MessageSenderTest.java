package model;

import eu.portcdm.messaging.PortCallMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

}