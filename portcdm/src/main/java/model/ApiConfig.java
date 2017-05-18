package model;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Aron on 2017-05-15.
 */
public class ApiConfig { // TODO: Lägga in konstanter för server-ip och inlogg här

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
