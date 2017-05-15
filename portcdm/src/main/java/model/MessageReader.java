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
    public MessageReader() {

    }

    // Hämtar och returnerar en lista med ett givet antal PortCallMessages
    public List<PortCallMessage> getMessages(){

        ApiClient apiClient = new ApiClient();

        // Adress till backendens PortCDM Services
        apiClient.setBasePath( "http://192.168.56.101:8080/dmp");

        // Inlogg till backenden
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );

        // API-key som ej används men krävs
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        StateupdateApi stateupdateApi = new StateupdateApi( apiClient );

        try {
            //return stateupdateApi.getMessagesBetween("2017-05-03T06:30:00Z","2017-05-05T06:50:00Z");

            // Hämtar 10 PortCallMessages
            return stateupdateApi.getPortCallMessages(10);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
