package model;

import eu.portcdm.client.ApiClient;
import eu.portcdm.client.ApiException;
import eu.portcdm.client.service.PortcallsApi;
import eu.portcdm.dto.PortCall;
import eu.portcdm.dto.PortCallSummary;

import java.util.LinkedList;
import java.util.List;

/**
 * Gets lists of PortCallSummaries and specific PortCalls.
 */
public class PortCallManager {

    // Stores the connection to the API, the currently active PortCall and a list of PortCallSummaries.
    private PortcallsApi portcallsApi;
    private PortCall activeCall;
    private List<PortCallSummary> summaries;

    /**
     * Creates a PortCallManager instance by connecting to the API, retrieving some PortCallSummaries and the latest updated PortCall.
     */
    public PortCallManager(){
        setupApi();
        summaries = refreshSummaries();
        activeCall = getPortCall(0);
    }

    /**
     * Refreshes the list of PortCallSummaries
     *
     * @return True if refresh was successful, False otherwise.
     */
    public boolean refreshCalls() { // TODO: Update the name of this method
        summaries = refreshSummaries();
        return summaries != null;
    }

    /**
     * Gets the PortCallIDs of the PortCalls in the list of PortCallSummaries.
     *
     * @return A list of PortCallIDs (Strings).
     */
    public List<String> getIds() {
        List<String> ids = new LinkedList<>();
        for (PortCallSummary summary :
                summaries) {
            ids.add(summary.getId());
        }
        return ids;
    }

    /**
     * Gets the PortCall currently saved in this instance of the manager.
     *
     * @return A PortCall
     */
    public PortCall getActiveCall(){
        return activeCall;
    }

    // Establishes connection to the backend
    private void setupApi(){

        ApiClient apiClient = new ApiClient();

        // Addresses to PortCDM Services

        // To the local VirtualBox instance:
        //apiClient.setBasePath(("http://192.168.56.101:8080/dmp"));

        // To Aron's backend:
        apiClient.setBasePath( "http://46.239.98.79:8080/dmp");

        // To the common sandbox:
        //apiClient.setBasePath("http://sandbox-5.portcdm.eu:8080/dmp");

        // Login information for the Virtualbox instances:
        apiClient.addDefaultHeader("X-PortCDM-UserId", "porter");
        apiClient.addDefaultHeader("X-PortCDM-Password", "porter");

        // Login information for the common backend:
        //apiClient.addDefaultHeader( "X-PortCDM-UserId", "test1" );
        //apiClient.addDefaultHeader( "X-PortCDM-Password", "test123" );

        // API-key, not used but necessary for connection:
        apiClient.addDefaultHeader( "X-PortCDM-ApiKey", "Fenix-SMA" );

        portcallsApi = new PortcallsApi(apiClient);
    }

    // Gets the 30 latest updated PortCalls from the API
    private List<PortCallSummary> refreshSummaries(){
        try {
            return portcallsApi.getAllPortCalls(30);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a PortCall by its position in the list of PortCallSummaries
     *
     * @param id the position of the requested PortCall
     * @return the requested PortCall
     */
    PortCall getPortCall(int id){
        PortCallSummary summary = summaries.get(id);
        try {
            return portcallsApi.getPortCall(summary.getId());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a PortCall by its PortCallID
     * @param id the PortCallID of the requested PortCall
     * @return the requested PortCall
     */
    public PortCall getPortCall(String id){
        try {
            return portcallsApi.getPortCall(id);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
