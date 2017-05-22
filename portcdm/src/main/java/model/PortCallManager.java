package model;

import eu.portcdm.client.ApiClient;
import eu.portcdm.client.ApiException;
import eu.portcdm.client.service.PortcallsApi;
import eu.portcdm.dto.Port;
import eu.portcdm.dto.PortCall;
import eu.portcdm.dto.PortCallSummary;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by arono on 2017-05-04.
 */
public class PortCallManager {

    // Här lagras APIn och callen.
    PortcallsApi portcallsApi;
    PortCall activeCall;
    List<PortCallSummary> summaries;

    // Konstruktor, skapar ett api och hämtar senaste callen.
    public PortCallManager(){
        setupApi();
        summaries = refreshSummaries();
        activeCall = getPortCall(0);
    }

    // Uppdaterar listan med PortCalls
    public boolean refreshCalls(){
        summaries = refreshSummaries();
        if (summaries == null){
            return false;
        }
        else {
            return true;
        }
    }

    public List<String> getIds(){
        List<String> ids = new LinkedList<String>();
        for (PortCallSummary summary :
                summaries) {
            ids.add(summary.getId());
        }
        return ids;
    }

    // Hämtar den aktuella callen
    public PortCall getActiveCall(){
        return activeCall;
    }

    // Ansluter till backenden
    private void setupApi(){

        ApiClient apiClient = new ApiClient();

        // Adress till virtualboxens PortCDM Services
        //apiClient.setBasePath( "http://46.239.98.79:8080/dmp");
        apiClient.setBasePath("http://sandbox-5.portcdm.eu:8080/dmp");

        // Inlogg till servern
       // apiClient.addDefaultHeader( "X-PortCDM-UserId", "porter" );
        //apiClient.addDefaultHeader( "X-PortCDM-Password", "porter" );

        apiClient.addDefaultHeader( "X-PortCDM-UserId", "test1" );
        apiClient.addDefaultHeader( "X-PortCDM-Password", "test123" );

        // API-key, används inte idag men måste finnas
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        portcallsApi = new PortcallsApi(apiClient);
    }

    // Hämtar ett givet antal PortCallSummaries
    private List<PortCallSummary> refreshSummaries(){
        try {
            return portcallsApi.getAllPortCalls(30);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hämtar ett givet PortCall
    public PortCall getPortCall(int id){
        PortCallSummary summary = summaries.get(id);
        try {
            return portcallsApi.getPortCall(summary.getId());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PortCall getPortCall(String id){
        try {
            return portcallsApi.getPortCall(id);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
