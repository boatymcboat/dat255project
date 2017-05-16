package model;

import eu.portcdm.amss.client.ApiClient;
import eu.portcdm.amss.client.ApiException;
import eu.portcdm.amss.client.StateupdateApi;
import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.messaging.*;
import se.viktoria.stm.portcdm.connector.common.util.PortCallMessageBuilder;
import se.viktoria.stm.portcdm.connector.common.util.StateWrapper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MessageSender {
    ApiClient apiClient;
    StateupdateApi stateupdateApi;


    public MessageSender() {
        setupApi();
    }

    // Skapar ett meddelande. Exempelkod från PortCDM-utvecklarna
    public PortCallMessage createMessage(){

        //StateWrapper xd = new StateWrapper()

        StateWrapper wrapper = new StateWrapper(
                ServiceObject.CARGO_OPERATION,
                "Aron",
                ServiceTimeSequence.COMMENCED,
                LogicalLocation.ANCHORING_AREA, //Type of optional location
                52.50, //Latitude of optional location
                52.50, //Longitude of optional location
                "Dana Fjord D1"
                );

        StateWrapper stateWrapper = new StateWrapper(
                LocationReferenceObject.VESSEL, //referenceObject
                LocationTimeSequence.ARRIVAL_TO, //ARRIVAL_TO or DEPARTURE_FROM
                LogicalLocation.BERTH, //Type of required location
                53.50, //Latitude of required location
                53.50, //Longitude of required location
                "Skarvik Harbour 518", //Name of required location
                LogicalLocation.ANCHORING_AREA, //Type of optional location
                52.50, //Latitude of optional location
                52.50, //Longitude of optional location
                "Dana Fjord D1" );//Name of optional location
        //Change dates from 2017-03-23 06:40:00 to 2017-03-23T06:40:00Z
        @SuppressWarnings("Since15") PortCallMessage portCallMessage = PortCallMessageBuilder.build(
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

    // Skickar ett givet meddelande till Assisted Message Submission Service
    public boolean sendMessage(PortCallMessage message){
        try {
            stateupdateApi.sendMessage( message );
            return true;
        } catch (ApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setupApi(){
        apiClient = new ApiClient();

        // Adress till backendens Assisted Message Submission Service
        apiClient.setBasePath( "http://46.239.98.79:8080/amss");

        // Inlogg till backenden
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );

        // API-key som ej används men krävs
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        stateupdateApi = new StateupdateApi( apiClient );

    }
}
