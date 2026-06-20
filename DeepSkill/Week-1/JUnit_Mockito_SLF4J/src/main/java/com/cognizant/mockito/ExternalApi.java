package com.cognizant.mockito;

/**
 * External API interface for Mockito exercises.
 * Simulates an external service dependency.
 */
public interface ExternalApi {
    String getData();
    String getDataWithParam(String param);
    void processData(String data);
}
