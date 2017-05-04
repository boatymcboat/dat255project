package sample;

import eu.portcdm.client.ApiClient;
import eu.portcdm.client.ApiException;
import eu.portcdm.client.service.StateupdateApi;
import eu.portcdm.messaging.PortCallMessage;

import java.util.List;

/**
 * Created by arono on 2017-05-04.
 */
public class MessageReader {
    public MessageReader() {

    }

    public List<PortCallMessage> getMessages(){
        // * 1. Setup ApiClient and connection to PortCDM
        ApiClient apiClient;

        apiClient = new ApiClient();
        //Base path = URL to PortCDM (i.e. http://192.168.56.101:8080/amss)
        apiClient.setBasePath( "http://192.168.56.101:8080/dmp");
        //apiClient.setBasePath( "http://dev.portcdm.eu/amss" );

        //Authenticate with headers
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        StateupdateApi stateupdateApi = new StateupdateApi( apiClient );

        try {
            return stateupdateApi.getPortCallMessages(100);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
