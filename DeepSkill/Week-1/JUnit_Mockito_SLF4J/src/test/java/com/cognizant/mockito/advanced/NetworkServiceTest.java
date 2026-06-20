package com.cognizant.mockito.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Advanced Mockito Exercise 4: Mocking Network Interactions.
 */
class NetworkServiceTest {

    @Test
    @DisplayName("Test service with mock network client")
    void testServiceWithMockNetworkClient() {
        // Create a mock network client
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");

        // Create the service
        NetworkService networkService = new NetworkService(mockNetworkClient);
        String result = networkService.connectToServer();

        // Verify
        assertEquals("Connected to Mock Connection", result);
        verify(mockNetworkClient).connect();
    }

    @Test
    @DisplayName("Test network connection failure")
    void testNetworkConnectionFailure() {
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenThrow(new RuntimeException("Connection refused"));

        NetworkService networkService = new NetworkService(mockNetworkClient);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> networkService.connectToServer()
        );
        assertEquals("Connection refused", exception.getMessage());
    }

    @Test
    @DisplayName("Test network client called exactly once")
    void testNetworkClientCalledOnce() {
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Server A");

        NetworkService networkService = new NetworkService(mockNetworkClient);
        networkService.connectToServer();

        verify(mockNetworkClient, times(1)).connect();
        verifyNoMoreInteractions(mockNetworkClient);
    }
}
