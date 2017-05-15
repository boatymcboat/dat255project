package model;

import eu.portcdm.messaging.PortCallMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arono on 2017-05-15.
 */
class MessageReaderTest {
    @Test
    void getMessages() {
        MessageReader reader = new MessageReader();
        List<PortCallMessage> list = reader.getMessages();
        assertEquals(10,list.size(),"10 messages are retrieved");
    }

}