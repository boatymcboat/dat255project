package sample;


import com.sun.org.apache.xpath.internal.SourceTree;
import eu.portcdm.amss.client.ApiClient;
import eu.portcdm.amss.client.ApiException;
import eu.portcdm.amss.client.StateupdateApi;
import eu.portcdm.client.service.PortcallsApi;
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
/*
    public List<PortCallSummary> runUpdates() {
        // * 1. Setup ApiClient and connection to PortCDM
        ApiClient apiClient2;

        apiClient2 = new ApiClient();
        //Base path = URL to PortCDM (i.e. http://192.168.56.101:8080/amss)
        apiClient2.setBasePath( "http://192.168.56.101:8080/dmp");
        //apiClient.setBasePath( "http://dev.portcdm.eu/amss" );

        //Authenticate with headers
        apiClient2.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient2.addDefaultHeader( "X-PortCDM-Password", "porter" );
        apiClient2.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );
        PortcallsApi portcallsApi = new PortcallsApi(apiClient2);

        try {
            List<PortCallSummary> portCallSummaries = portcallsApi.getAllPortCalls(30);
            //System.out.println(portCallSummaries.size());
            //System.out.println(portcallsApi.getAllPortCalls(30));
            /*
            List<PortCallMessage> messages = stateupdateApi.getPortCallMessages(100);
            for (PortCallMessage message : messages) {
                String out = message.getComment();
                System.out.println(out);
            }

            return null;
            //return portCallSummaries;
        } catch (eu.portcdm.client.ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
