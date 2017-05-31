package model;

import eu.portcdm.amss.client.ApiClient;
import eu.portcdm.amss.client.ApiException;
import eu.portcdm.amss.client.StateupdateApi;
import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.dto.PortCall;
import eu.portcdm.messaging.*;
import se.viktoria.stm.portcdm.connector.common.util.PortCallMessageBuilder;
import se.viktoria.stm.portcdm.connector.common.util.StateWrapper;

/**
 * Creates and sends PortCallMessages (version 0.0.16 only).
 */
public class MessageSender {
    private ApiClient apiClient;
    private StateupdateApi stateupdateApi;

    public MessageSender() {
        setupApi();
    }

    /**
     * TODO: Uppdatera detta
     * Skickar locationStates. Följande delar är ej implementerade:
     latitude - För både origin och destination.
     longitude - Fär både origin och destination.
     Name - För både origin och destination,  ex Dana Fjord D1.
     localPortCallId - lokalt id.
     localJobId - lokalat id.
     reportedAt - **Current Time**.
     reportedBy - Agenten som är "inloggad".
     groupWith - Oklart om det behövs.
     comment - Skulle vara nice.
     * @param portCall Det port call som behandlas
     * @param locationTimeSequence Arrival to or Departure from etc
     * @param originLocationType Typ av plats som skeppet åker från, ex Anchorage_Area
     * @param originLocationName
     * @param destinationLocationType Typ av plats som skeppet åker till, ex Berth
     * @param destinationLocationName
     * @param time Tid då händelsen skedde/sker
     * @param timeType Typ av tid: Estimated, Actual etc.
     * @param portCallId
     */
    public void sendLocationState(PortCall portCall, LocationTimeSequence locationTimeSequence,
                                  LogicalLocation originLocationType, String originLocationName, LogicalLocation destinationLocationType,
                                  String destinationLocationName,
                                  String time, TimeType timeType, String portCallId){
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
                null,
                null,
                null
        );
        message.setPortCallId(portCallId);
        sendMessage(message);
    }

    /**
     * TODO: Uppdatera detta
     * Skickar serviceStates. Följande delar är ej implementerade,
     * latitude - Ska kollas med PortCDM.
     * longitude - som latitude.
     * Name - ex Dana Fjord D1.
     * localPortCallId - lokalt id.
     * localJobId - lokalat id.
     * reportedAt - **Current Time**.
     * reportedBy - Agenten som är "inloggad".
     * groupWith - Oklart om det behövs.
     * comment - Skulle vara nice.
     *
     * @param portCall        Det port call som behandlas
     * @param serviceType     Typ av service, ex Cargo_Operation
     * @param serviceSequence Vilken sekvens servicen befinner sig i, ex Commenced
     * @param locationType    Typ av location, ex Ancoring_Area
     * @param locationName
     * @param time            Tid då händelsen skedde/sker
     * @param timeType        Typ av tid: Estimated, Actual etc.
     * @param portCallId
     */
    public void sendServiceState(PortCall portCall, ServiceObject serviceType, ServiceTimeSequence serviceSequence,
                                 LogicalLocation locationType, String locationName, String time, TimeType timeType, String portCallId) {
        StateWrapper wrapper = new StateWrapper(
                serviceType,
                "Aron",
                serviceSequence,
                locationType, //Type of optional location
                null, //Latitude of optional location
                null, //Longitude of optional location
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
                null,
                null,
                null
        );
        message.setPortCallId(portCallId);
        sendMessage(message);
    }

    /**
     * TODO: Lägga till information här
     *
     * @param portCall
     * @param serviceType
     * @param serviceSequence
     * @param fromLocationType
     * @param fromLocationName
     * @param toLocationType
     * @param toLocationName
     * @param time
     * @param timeType
     * @param portCallId
     */
    public void sendServiceState(PortCall portCall, ServiceObject serviceType, ServiceTimeSequence serviceSequence,
                                 LogicalLocation fromLocationType, String fromLocationName, LogicalLocation toLocationType,
                                 String toLocationName, String time, TimeType timeType, String portCallId){
        StateWrapper wrapper = new StateWrapper(
                serviceType,
                "Aron",
                serviceSequence,
                fromLocationType, //Type of optional location
                null, //Latitude of optional location
                null, //Longitude of optional location
                fromLocationName,
                toLocationType,
                null,
                null,
                toLocationName
        );

        PortCallMessage message = PortCallMessageBuilder.build(
                null,
                null,
                wrapper,
                time,
                timeType,
                portCall.getVessel().getId(),
                null,
                null,
                null,
                null
        );
        message.setPortCallId(portCallId);
        sendMessage(message);
    }

    /**
     * Sends a message to the API
     *
     * @param message The message to be sent
     * @return True if the message was sent, False otherwise.
     */
    boolean sendMessage(PortCallMessage message){
        try {
            stateupdateApi.sendMessage( message );
            return true;
        } catch (ApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Creates connection to backend
    private void setupApi() {
        apiClient = new ApiClient();

        // Address to the backend's Assisted Message Submission Service

        // Aron's backend
        apiClient.setBasePath("http://46.239.98.79:8080/amss");

        // The common sandbox (the application does not work towards the common sandbox):
        //apiClient.setBasePath("http://sandbox-5.portcdm.eu:8080/amss");

        // The locally hosted Virtualbox:
        //apiClient.setBasePath("http://192.168.56.101:8080/amss");

        // Logins to the backend

        // To Virtualbox-based backends:
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter");
        apiClient.addDefaultHeader("X-PortCDM-Password", "porter");

        // To the common sandbox:
        //apiClient.addDefaultHeader( "X-PortCDM-UserId", "test1" );
        //apiClient.addDefaultHeader( "X-PortCDM-Password", "test123" );

        // API-key (not used in practice but needed for connection):
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        stateupdateApi = new StateupdateApi( apiClient );

    }
}
