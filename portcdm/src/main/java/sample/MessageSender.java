package sample;

/**
 * Created by arono on 2017-05-04.
 */
import eu.portcdm.amss.client.ApiClient;
import eu.portcdm.amss.client.ApiException;
import eu.portcdm.amss.client.StateupdateApi;
import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.messaging.LocationReferenceObject;
import eu.portcdm.messaging.LogicalLocation;
import eu.portcdm.messaging.PortCallMessage;
import eu.portcdm.messaging.TimeType;
import se.viktoria.stm.portcdm.connector.common.util.PortCallMessageBuilder;
import se.viktoria.stm.portcdm.connector.common.util.StateWrapper;

public class MessageSender {

    public MessageSender() {

    }

    // Skapar ett meddelande. Exempelkod från PortCDM-utvecklarna
    public PortCallMessage createMessage(){

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
        PortCallMessage portCallMessage = PortCallMessageBuilder.build(
                "urn:x-mrn:stm:portcdm:local_port_call:SEGOT:DHC:52723", //localPortCallId
                "urn:x-mrn:stm:portcdm:local_job:FENIX_SMA:990198125", //localJobId
                stateWrapper, //StateWrapper created above
                "2017-03-23T06:40:00Z", //Message's time
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
    public void sendMessage(PortCallMessage message){

        ApiClient apiClient = new ApiClient();

        // Adress till backendens Assisted Message Submission Service
        apiClient.setBasePath( "http://192.168.56.101:8080/amss");

        // Inlogg till backenden
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );

        // API-key som ej används men krävs
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        StateupdateApi stateupdateApi = new StateupdateApi( apiClient );

        try {
            stateupdateApi.sendMessage( message );
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
