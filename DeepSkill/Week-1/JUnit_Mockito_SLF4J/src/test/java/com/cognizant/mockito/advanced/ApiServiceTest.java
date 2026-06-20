package com.cognizant.mockito.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Advanced Mockito Exercise 2: Mocking External Services (RESTful APIs).
 */
class ApiServiceTest {

    @Test
    @DisplayName("Test service with mock REST client")
    void testServiceWithMockRestClient() {
        // Create a mock REST client
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");

        // Create the API service with the mock
        ApiService apiService = new ApiService(mockRestClient);
        String result = apiService.fetchData();

        // Verify
        assertEquals("Fetched Mock Response", result);
        verify(mockRestClient).getResponse();
    }

    @Test
    @DisplayName("Test service when REST client returns empty response")
    void testServiceWithEmptyResponse() {
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("");

        ApiService apiService = new ApiService(mockRestClient);
        String result = apiService.fetchData();

        assertEquals("Fetched ", result);
    }

    @Test
    @DisplayName("Test service when REST client throws exception")
    void testServiceWithRestClientException() {
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenThrow(new RuntimeException("Connection timeout"));

        ApiService apiService = new ApiService(mockRestClient);

        assertThrows(RuntimeException.class, () -> apiService.fetchData());
    }
}
