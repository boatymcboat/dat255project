package model;

import eu.portcdm.amss.client.ApiClient;
import eu.portcdm.amss.client.ApiException;
import eu.portcdm.amss.client.StateupdateApi;
import eu.portcdm.dto.*;
import eu.portcdm.messaging.*;
import eu.portcdm.messaging.ServiceObject;
import eu.portcdm.messaging.ServiceTimeSequence;
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
                null, //Latitude of optional location
                null, //Longitude of optional location
                "Dana Fjord D1"
                );

        StateWrapper stateWrapper = new StateWrapper(
                LocationReferenceObject.VESSEL, //referenceObject
                LocationTimeSequence.ARRIVAL_TO, //ARRIVAL_TO or DEPARTURE_FROM
                LogicalLocation.BERTH, //Type of required location
                null, //Latitude of required location
                null, //Longitude of required location
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
                "Agent BoatyMcBoat", //reportedBy (optional)
                null, //groupWith (optional), messageId of the message to group with.
                "Hello World" //comment (optional)
        );

        return portCallMessage;
    }

    /*
        Skickar locationStates med följande input,
            portCall - Det port call som behandlas
            locationTimeSequence - Arrival to or Departure from etc
            originLocationType - Typ av plats som skeppet åker från, ex Anchorage_Area
            destinationLocationType - Typ av plats som skeppet åker till, ex Berth
            time - Tid då händelsen skedde/sker
            timeType - Typ av tid: Estimated, Actual etc.

        Följande delar är ej implementerade,
            latitude - För både origin och destination
            longitude - Fär både origin och destination
            Name - För både origin och destination,  ex Dana Fjord D1
            localPortCallId - lokalt id
            localJobId - lokalat id
            reportedAt - **Current Time**
            reportedBy - Agenten som är "inloggad"
            groupWith - Oklart om det behövs
            comment - Skulle vara nice
     */
    public void sendLocationState(PortCall portCall, LocationTimeSequence locationTimeSequence,
                                  LogicalLocation originLocationType, String originLocationName, LogicalLocation destinationLocationType,
                                  String destinationLocationName,
                                  String time, TimeType timeType){
        StateWrapper wrapper = new StateWrapper(
                LocationReferenceObject.VESSEL, //referenceObject
                locationTimeSequence , //ARRIVAL_TO or DEPARTURE_FROM
                destinationLocationType, //Type of required location
                null, //Latitude of required location
                null, //Longitude of required location
                destinationLocationName, //Name of required location
                originLocationType, //Type of optional location
                null, //Latitude of optional location
                null, //Longitude of optional location
                originLocationName );

        PortCallMessage message = PortCallMessageBuilder.build(
                null,
                null,
                wrapper,
                time,
                timeType,
                portCall.getVessel().getId(),
                null,
                "Agent BoatyMcBoat",
                null,
                null
        );
        sendMessage(message);
    }


    /*
    Skickar serviceState med följande input,
        portCall - Det port call som behandlas
        servicetype - Typ av service, ex Cargo_Operation
        serviceTimeSequence - Vilken sekvens servicen befinner sig i, ex Commenced
        locationType - Typ av location, ex Ancoring_Area
        time - Tid då händelsen skedde/sker
        timeType - Typ av tid: Estimated, Actual etc.

    Följande delar är ej implementerade,
        latitude - Ska kollas med PortCDM
        longitude - som latitude
        Name - ex Dana Fjord D1
        localPortCallId - lokalt id
        localJobId - lokalat id
        reportedAt - **Current Time**
        reportedBy - Agenten som är "inloggad"
        groupWith - Oklart om det behövs
        comment - Skulle vara nice
    */
    public void sendServiceState(PortCall portCall, ServiceObject serviceType, ServiceTimeSequence serviceSequence,
                                 LogicalLocation locationType,String locationName, String time, TimeType timeType){
        StateWrapper wrapper = new StateWrapper(
                serviceType,
                "Aron",
                serviceSequence,
                locationType, //Type of optional location
                52.50, //Latitude of optional location
                52.50, //Longitude of optional location
                locationName
        );

        PortCallMessage message = PortCallMessageBuilder.build(
                null,
                null,
                wrapper,
                time,
                timeType,
                portCall.getVessel().getId(),
                null,
                "Agent BoatyMcBoat",
                null,
                null
        );

        sendMessage(message);
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
