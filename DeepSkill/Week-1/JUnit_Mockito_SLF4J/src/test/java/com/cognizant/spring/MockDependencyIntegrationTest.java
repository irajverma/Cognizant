package com.cognizant.spring;

import com.cognizant.model.User;
import com.cognizant.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Mock Dependencies Exercise 3: Mocking a Service Dependency in an Integration Test.
 * Uses @SpringBootTest with @AutoConfigureMockMvc and @MockBean.
 */
@SpringBootTest
@AutoConfigureMockMvc
class MockDependencyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Mock Deps Ex 3: Integration test with mocked service")
    void testIntegrationWithMockedService() throws Exception {
        // Arrange - mock the service even in integration test
        User mockUser = new User(1L, "Integration Mock User");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        // Act & Assert - full application context is loaded
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Integration Mock User"));

        verify(userService).getUserById(1L);
    }

    @Test
    @DisplayName("Mock Deps Ex 3: Integration test verifies service interaction count")
    void testServiceInteractionCount() throws Exception {
        User mockUser = new User(2L, "Another User");
        when(userService.getUserById(2L)).thenReturn(mockUser);

        // Call the endpoint twice
        mockMvc.perform(get("/users/2")).andExpect(status().isOk());
        mockMvc.perform(get("/users/2")).andExpect(status().isOk());

        // Verify the service was called exactly twice
        verify(userService, times(2)).getUserById(2L);
    }
}
