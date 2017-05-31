package model;

import eu.portcdm.messaging.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.viktoria.stm.portcdm.connector.common.util.PortCallMessageBuilder;
import se.viktoria.stm.portcdm.connector.common.util.StateWrapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: Add test methods for sendLocationState and sendServiceState
class MessageSenderTest {

    @BeforeAll
    static void checkConnection() {
        assertTrue(ApiConfig.isOnline(),"Server is unreachable!");
    }

    @Test
    void sendMessage() {
        MessageSender sender = new MessageSender();
        PortCallMessage pcm = createTestMessage();
        assertTrue(sender.sendMessage(pcm), "Could not send PCM");
    }

    // Creates a sample message for testing purposes
    private PortCallMessage createTestMessage() {
        StateWrapper wrapper = new StateWrapper(
                ServiceObject.CARGO_OPERATION,
                "Aron",
                ServiceTimeSequence.COMMENCED,
                LogicalLocation.ANCHORING_AREA, //Type of optional location
                null, //Latitude of optional location
                null, //Longitude of optional location
                "Dana Fjord D1"
        );

        /*StateWrapper stateWrapper = new StateWrapper(
                LocationReferenceObject.VESSEL, //referenceObject
                LocationTimeSequence.ARRIVAL_TO, //ARRIVAL_TO or DEPARTURE_FROM
                LogicalLocation.BERTH, //Type of required location
                null, //Latitude of required location
                null, //Longitude of required location
                "Skarvik Harbour 518", //Name of required location
                LogicalLocation.ANCHORING_AREA, //Type of optional location
                null, //Latitude of optional location
                null, //Longitude of optional location
                "Dana Fjord D1" );//Name of optional location*/
        PortCallMessage portCallMessage = PortCallMessageBuilder.build(
                "urn:x-mrn:stm:portcdm:local_port_call:SEGOT:DHC:52723", //localPortCallId
                "urn:x-mrn:stm:portcdm:local_job:FENIX_SMA:990198125", //localJobId
                wrapper, //StateWrapper created above
                ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT), //Message's time
                TimeType.ESTIMATED, //Message's timeType
                "urn:x-mrn:stm:vessel:IMO:9259501", //vesselId
                null, //reportedAt (optional)
                null, //reportedBy (optional)
                null, //groupWith (optional), messageId of the message to group with.
                "Hello World" //comment (optional)
        );
        return portCallMessage;
    }
}