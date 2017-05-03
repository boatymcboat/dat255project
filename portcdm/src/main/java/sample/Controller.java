package sample;


import com.sun.org.apache.xpath.internal.SourceTree;
import eu.portcdm.client.ApiClient;
import eu.portcdm.client.ApiException;
import eu.portcdm.client.service.PortcallsApi;
import eu.portcdm.client.service.StateupdateApi;
import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.dto.PortCallSummary;
import eu.portcdm.messaging.LocationReferenceObject;
import eu.portcdm.messaging.LogicalLocation;
import eu.portcdm.messaging.PortCallMessage;
import eu.portcdm.messaging.TimeType;
import se.viktoria.stm.portcdm.connector.common.util.PortCallMessageBuilder;
import se.viktoria.stm.portcdm.connector.common.util.StateWrapper;

import java.util.List;

public class Controller {

    public Controller() {
    }

    public List<PortCallSummary> runUpdates() {
        PortCallMessage portCallMessage;

        // * 1. Setup ApiClient and connection to PortCDM
        ApiClient apiClient;

        apiClient = new ApiClient();
        //Base path = URL to PortCDM (i.e. http://192.168.56.101:8080/amss)
        //apiClient.setBasePath( "http://192.168.56.101:8080/dmp" );
        //apiClient.setBasePath( "http://dev.portcdm.eu/amss" );

        //Authenticate with headers
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );



        /*StateupdateApi stateupdateApi = new StateupdateApi( apiClient );

        // * 3. Fetch new message
        portCallMessage = getExampleMessage();

        // * 4. Send message to PortCDM
        try {
            stateupdateApi.sendMessage( portCallMessage );
        } catch (ApiException e) {
            e.printStackTrace();
        }*/


        apiClient.setBasePath( "http://192.168.56.101:8080/dmp" );
        PortcallsApi portcallsApi = new PortcallsApi(apiClient);

        try {
            List<PortCallSummary> portCallSummaries = portcallsApi.getAllPortCalls(30);
            //System.out.println(portCallSummaries.size());
            //System.out.println(portcallsApi.getAllPortCalls(30));
            return portCallSummaries;
        } catch (eu.portcdm.client.ApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    private PortCallMessage getExampleMessage() {
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
                "urn:mrn:stm:portcdm:local_port_call:SEGOT:DHC:52723", //localPortCallId
                "urn:mrn:stm:portcdm:local_job:FENIX_SMA:990198125", //localJobId
                stateWrapper, //StateWrapper created above
                "2017-03-23T06:40:00Z", //Message's time
                TimeType.ESTIMATED, //Message's timeType
                "urn:mrn:stm:vessel:IMO:9259501", //vesselId
                "2017-03-23T06:38:56Z", //reportedAt (optional)
                "Viktoria", //reportedBy (optional)
                "urn:mrn:stm:portcdm:message:5eadbb1c-6be7-4cf2-bd6d-f0af5a0c35dc", //groupWith (optional), messageId of the message to group with.
                "example comment" //comment (optional)
        );

        return portCallMessage;
    }


    /*
    public static void connect(){
        Configuration configuration;
        // * Read the configuration file

        configuration = new Configuration("config.conf","/",new Predicate<Map.Entry<Object,Object>>() {
            @Override
            public boolean test(Map.Entry<Object,Object> objectObjectEntry) {
                return !objectObjectEntry.getKey().toString().equals("pass");
            }
        });
        configuration.reload();


        // ** Create a submission service and add connectors
        SubmissionService submissionService;
        submissionService = new SubmissionService();
        submissionService.addConnectors(configuration);

        // ** Create a list of port call messages (somehow)
        // List<PortCallMessage> messages;
        // messages = createMessages();
        // ** Submit the messages
        //submissionService.submitUpdates(messages);
    }

    public StateupdateApi stateUpdateApi;

    private StateupdateApi initiateStateUpdateAPI() {
        ApiClient connectorClient;

        String baseUrl = "/";
        int timeout = 2700;

        connectorClient = new ApiClient();
        connectorClient.setBasePath(baseUrl);
        connectorClient.setConnectTimeout(timeout);
        stateUpdateApi = new StateupdateApi(connectorClient);

        return stateUpdateApi;
    }

    private PortCallMessage test(String portCallId) {
        PortCallMessage portCallMessage = new PortCallMessage();
        LocationState locationState = new LocationState();
        ArrivalLocation arrivalLocation = new ArrivalLocation();
        DepartureLocation departureLocation = new DepartureLocation();

        // Set portcallID and message id
        portCallMessage.setPortCallId(portCallId);
        portCallMessage.setMessageId("urn:x-mrn:stm:portcdm:message:" + randomUUID().toString() );

        locationState.setArrivalLocation(arrivalLocation);
        locationState.setDepartureLocation(departureLocation);
        locationState.setReferenceObject(LocationReferenceObject.VESSEL);

        portCallMessage.setTimeType(TimeType.ESTIMATED);
        portCallMessage.setTime(DateFormatter.toGregorianXML("2016-09-05T09:00:00Z"));
        portCallMessage.setReportedAt(DateFormatter.toGregorianXML("2016-09-02T10:00:00Z"));

        return portCallMessage;
    }

    private void sendPCM(PortCallMessage message) {

        String userId = "fenix";
        String password = "password";
        String apiKey = "Fenix-SMA";
        // Submit the message to the API
        try {
            stateUpdateApi.sendMessage( message );
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }*/

}
