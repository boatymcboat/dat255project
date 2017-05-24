package model;

import eu.portcdm.client.ApiClient;
import eu.portcdm.client.ApiException;
import eu.portcdm.client.service.StateupdateApi;
import eu.portcdm.messaging.PortCallMessage;

import java.util.List;

/**
 * Created by arono on 2017-05-04.
 */
public class MessageReader {
    private ApiClient apiClient;
    private StateupdateApi stateupdateApi;

    public MessageReader() {
        setupApi();
    }

    private void setupApi(){
        apiClient = new ApiClient();
        // Adress till backendens PortCDM Services
        //apiClient.setBasePath( "http://46.239.98.79:8080/dmp");
        //apiClient.setBasePath("http://sandbox-5.portcdm.eu:8080/dmp" );
        apiClient.setBasePath(("http://192.168.56.101:8080/dmp"));

        // Inlogg till backenden
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );

        //apiClient.addDefaultHeader( "X-PortCDM-UserId", "test1" );
        //apiClient.addDefaultHeader( "X-PortCDM-Password", "test123" );

        // API-key som ej används men krävs
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        stateupdateApi = new StateupdateApi( apiClient );
    }

    // Hämtar och returnerar en lista med ett givet antal PortCallMessages
    public List<PortCallMessage> getMessages(){
        try {
            //return stateupdateApi.getMessagesBetween("2017-05-03T06:30:00Z","2017-05-05T06:50:00Z");
            return stateupdateApi.getPortCallMessages(10);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
