package com.cognizant.mockito.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Advanced Mockito Exercise 1: Mocking Databases and Repositories.
 */
class ServiceTest {

    @Test
    @DisplayName("Test service with mock repository")
    void testServiceWithMockRepository() {
        // Create a mock repository
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");

        // Create the service with the mock
        Service service = new Service(mockRepository);
        String result = service.processData();

        // Verify the result
        assertEquals("Processed Mock Data", result);

        // Verify the interaction
        verify(mockRepository).getData();
    }

    @Test
    @DisplayName("Test service with null data from repository")
    void testServiceWithNullData() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn(null);

        Service service = new Service(mockRepository);
        String result = service.processData();

        assertEquals("Processed null", result);
    }
}
