package sample;

import eu.portcdm.client.ApiClient;
import eu.portcdm.client.ApiException;
import eu.portcdm.client.service.PortcallsApi;
import eu.portcdm.dto.Port;
import eu.portcdm.dto.PortCall;
import eu.portcdm.dto.PortCallSummary;

import java.util.List;

/**
 * Created by arono on 2017-05-04.
 */
public class PortCallManager {

    // Här lagras APIn och callen.
    PortcallsApi portcallsApi;
    PortCall activeCall;

    // Konstruktor, skapar ett api och hämtar senaste callen.
    public PortCallManager(){
        setupApi();
        activeCall = getLatestPortCall();
    }

    // Hämtar den aktuella callen
    public PortCall getActiveCall(){
        return activeCall;
    }

    // Ansluter till backenden
    private void setupApi(){

        ApiClient apiClient = new ApiClient();

        // Adress till virtualboxens PortCDM Services
        apiClient.setBasePath( "http://192.168.56.101:8080/dmp");

        // Inlogg till servern
        apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );

        // API-key, används inte idag men måste finnas
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        portcallsApi = new PortcallsApi(apiClient);
    }

    // Hämtar ett givet antal PortCallSummaries
    private List<PortCallSummary> getSummaries(){
        try {
            return portcallsApi.getAllPortCalls(30);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hämtar ett givet PortCall - just nu den tredje senast uppdaterade sådana
    private PortCall getLatestPortCall(){
        List<PortCallSummary> summaries = getSummaries();
        PortCallSummary summary = summaries.get(2);
        try {
            return portcallsApi.getPortCall(summary.getId());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
