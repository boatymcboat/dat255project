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

    /** Creates a message detailing a ServiceState within a PortCall, then sends it via the sendMessage method.
     *
     * @param portCall The PortCall to be updated
     * @param locationTimeSequence The logical sequence, arrival or departure.
     * @param originLocationType Location type of the origin, eg. Anchorage_Area. Nullable.
     * @param originLocationName Actual name of the origin. Nullable.
     * @param destinationLocationType Location type of the destination, eg. Berth
     * @param destinationLocationName Actual name of the destination
     * @param time String, contains details on the time. Format: yy-mm-dd:'T'hh:mm:xxx'Z'
     * @param timeType Type of time: Estimated, Actual etc.
     * @param portCallId The identification String for the PortCall to send the message to.
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
     * Creates a message detailing a ServiceState within a PortCall, then sends it via the sendMessage method.
     * 'This method is used for services performed at a singular location.
     *
     * @param portCall        The PortCall to send the message to.
     * @param serviceType     Type of service, eg. Cargo_Operation
     * @param serviceSequence The logical sequence of the service, eg. commenced, completed etc.
     * @param locationType    Type of location, eg. Ancoring_Area
     * @param locationName    The actual name of the location
     * @param time            String, contains details on the time. Format: yy-mm-dd:'T'hh:mm:xxx'Z'
     * @param timeType        Type of time: Estimated, Actual etc.
     * @param portCallId      The identification String for the PortCall to send the message to.
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
     * Creates a message detailing a ServiceState within a PortCall, then sends it via the sendMessage method.
     * This method is used for services performed between two locations.
     * @param portCall          The PortCall to send the message to.
     * @param serviceType       Type of service, eg. Cargo_Operation
     * @param serviceSequence   The logical sequence of the service, eg. commenced, completed etc.
     * @param fromLocationType  Location type of the origin, eg. Ancoring_Area.
     * @param fromLocationName  The actual name of the origin.
     * @param toLocationType    Location type of the destination, eg. Ancoring_Area.
     * @param toLocationName    The actual name of the destination.
     * @param time              String, contains details on the time. Format: yy-mm-dd:'T'hh:mm:xxx'Z'
     * @param timeType          Type of time: Estimated, Actual etc.
     * @param portCallId        The identification String for the PortCall to send the message to.
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
