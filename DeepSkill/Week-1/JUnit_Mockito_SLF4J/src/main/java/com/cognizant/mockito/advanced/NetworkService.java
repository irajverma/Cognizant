package com.cognizant.mockito.advanced;

/**
 * Network service that depends on NetworkClient for Advanced Mockito Exercise 4.
 */
public class NetworkService {

    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        String connection = networkClient.connect();
        return "Connected to " + connection;
    }
}
