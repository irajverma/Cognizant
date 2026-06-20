package com.cognizant.mockito.advanced;

/**
 * API Service that depends on RestClient for Advanced Mockito Exercise 2.
 */
public class ApiService {

    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        String response = restClient.getResponse();
        return "Fetched " + response;
    }
}
