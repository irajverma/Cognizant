package com.cognizant.mockito.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Advanced Mockito Exercise 5: Mocking Multiple Return Values.
 */
class MultiReturnServiceTest {

    @Test
    @DisplayName("Test service with multiple return values from repository")
    void testServiceWithMultipleReturnValues() {
        // Create a mock repository
        Repository mockRepository = mock(Repository.class);

        // Stub to return different values on consecutive calls
        when(mockRepository.getData())
                .thenReturn("First Mock Data")
                .thenReturn("Second Mock Data");

        // Create the service
        Service service = new Service(mockRepository);

        // First call
        String firstResult = service.processData();
        assertEquals("Processed First Mock Data", firstResult);

        // Second call
        String secondResult = service.processData();
        assertEquals("Processed Second Mock Data", secondResult);
    }

    @Test
    @DisplayName("Test multiple calls return last value after exhausting stubs")
    void testMultipleCallsAfterExhaustion() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("First")
                .thenReturn("Second")
                .thenReturn("Third");

        Service service = new Service(mockRepository);

        assertEquals("Processed First", service.processData());
        assertEquals("Processed Second", service.processData());
        assertEquals("Processed Third", service.processData());
        // 4th call should still return "Third" (last stubbed value)
        assertEquals("Processed Third", service.processData());

        verify(mockRepository, times(4)).getData();
    }
}
