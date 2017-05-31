package model;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Class for API Configuration. Currently only has functionality to check if the backend is online, but can be used to store constants for API connections in the future.
 */
public class ApiConfig {
    /**
     * Checks if the backend is online. Only implemented for Aron's backend.
     *
     * @return true if backend is online, false if it is not
     */
    public static boolean isOnline(){
        String ip = "46.239.98.79";
        int timeout = 7000;
        try {
            return InetAddress.getByName(ip).isReachable(timeout);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
