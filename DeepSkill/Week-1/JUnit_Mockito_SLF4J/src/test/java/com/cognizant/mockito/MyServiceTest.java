package com.cognizant.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Mockito Exercises 1-7: Mocking, Stubbing, Verification, Argument Matching,
 * Void Methods, Multiple Returns, Interaction Order, and Void Exceptions.
 */
class MyServiceTest {

    // ================================================================
    // Exercise 1: Mocking and Stubbing
    // ================================================================

    @Test
    @DisplayName("Exercise 1: Test external API mocking and stubbing")
    void testExternalApiMocking() {
        // Create a mock object for the external API
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the method to return a predefined value
        when(mockApi.getData()).thenReturn("Mock Data");

        // Create the service with the mock
        MyService service = new MyService(mockApi);

        // Call the method and assert the result
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    // ================================================================
    // Exercise 2: Verifying Interactions
    // ================================================================

    @Test
    @DisplayName("Exercise 2: Test verifying method interactions")
    void testVerifyInteraction() {
        // Create a mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Create the service
        MyService service = new MyService(mockApi);

        // Call the method
        service.fetchData();

        // Verify the interaction - getData() should be called exactly once
        verify(mockApi).getData();
        verify(mockApi, times(1)).getData();
    }

    @Test
    @DisplayName("Exercise 2: Test verify no more interactions")
    void testVerifyNoMoreInteractions() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
        verifyNoMoreInteractions(mockApi);
    }

    // ================================================================
    // Exercise 3: Argument Matching
    // ================================================================

    @Test
    @DisplayName("Exercise 3: Test argument matching with specific value")
    void testArgumentMatchingSpecific() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getDataWithParam("test")).thenReturn("Test Result");

        MyService service = new MyService(mockApi);
        String result = service.fetchDataWithParam("test");

        assertEquals("Test Result", result);
        verify(mockApi).getDataWithParam("test");
    }

    @Test
    @DisplayName("Exercise 3: Test argument matching with any string")
    void testArgumentMatchingAny() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getDataWithParam(anyString())).thenReturn("Any Result");

        MyService service = new MyService(mockApi);
        String result = service.fetchDataWithParam("anything");

        assertEquals("Any Result", result);
        verify(mockApi).getDataWithParam(anyString());
    }

    // ================================================================
    // Exercise 4: Handling Void Methods
    // ================================================================

    @Test
    @DisplayName("Exercise 4: Test stubbing and verifying void methods")
    void testVoidMethod() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the void method (doNothing is default, but explicit for clarity)
        doNothing().when(mockApi).processData(anyString());

        MyService service = new MyService(mockApi);
        service.process("test data");

        // Verify the void method was called
        verify(mockApi).processData("test data");
    }

    // ================================================================
    // Exercise 5: Mocking and Stubbing with Multiple Returns
    // ================================================================

    @Test
    @DisplayName("Exercise 5: Test multiple return values on consecutive calls")
    void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub to return different values on consecutive calls
        when(mockApi.getData())
                .thenReturn("First Call")
                .thenReturn("Second Call")
                .thenReturn("Third Call");

        MyService service = new MyService(mockApi);

        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call", service.fetchData());
        // Subsequent calls return the last stubbed value
        assertEquals("Third Call", service.fetchData());
    }

    // ================================================================
    // Exercise 6: Verifying Interaction Order
    // ================================================================

    @Test
    @DisplayName("Exercise 6: Test method call order verification")
    void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        // Call methods in specific order
        service.fetchData();
        service.process("data");
        service.fetchDataWithParam("param");

        // Verify the order of interactions
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).processData("data");
        inOrder.verify(mockApi).getDataWithParam("param");
    }

    // ================================================================
    // Exercise 7: Handling Void Methods with Exceptions
    // ================================================================

    @Test
    @DisplayName("Exercise 7: Test void method throwing exception")
    void testVoidMethodWithException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub the void method to throw an exception
        doThrow(new RuntimeException("API Error"))
                .when(mockApi).processData("bad data");

        MyService service = new MyService(mockApi);

        // Verify that exception is thrown
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.process("bad data")
        );
        assertEquals("API Error", exception.getMessage());

        // Verify the interaction occurred
        verify(mockApi).processData("bad data");
    }
}
