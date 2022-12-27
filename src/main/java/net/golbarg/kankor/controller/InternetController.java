package net.golbarg.kankor.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetController {

    public static boolean checkConnection() throws UnknownHostException, IOException {
        InetAddress address = InetAddress.getByName("http://www.google.com");
        if (address.isReachable(3000)) {
            return true;
        } else {
            return false;
        }
    }
}
