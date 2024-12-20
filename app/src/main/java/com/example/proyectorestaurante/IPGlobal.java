package com.example.proyectorestaurante;

public class IPGlobal {
    private static IPGlobal instance;
    private String ipAddress;

    private IPGlobal() {}

    public static synchronized IPGlobal getInstance() {
        if (instance == null) {
            instance = new IPGlobal();
        }
        return instance;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
