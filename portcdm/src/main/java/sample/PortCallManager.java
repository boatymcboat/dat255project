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

    // Slänger
    public PortCall getActiveCall(){
        return activeCall;
    }

    private void setupApi(){
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

        portcallsApi = new PortcallsApi(apiClient);
    }

    private List<PortCallSummary> getSummaries(){
        try {
            return portcallsApi.getAllPortCalls(30);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PortCall getLatestPortCall(){
        List<PortCallSummary> summaries = getSummaries();
        PortCallSummary summary = summaries.get(4);
        try {
            return portcallsApi.getPortCall(summary.getId());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
