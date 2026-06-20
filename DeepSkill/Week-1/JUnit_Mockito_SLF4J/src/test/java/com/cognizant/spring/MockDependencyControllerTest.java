package com.cognizant.spring;

import com.cognizant.controller.UserController;
import com.cognizant.model.User;
import com.cognizant.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Mock Dependencies Exercise 1: Mocking a Service Dependency in a Controller Test.
 * Uses @WebMvcTest with @MockBean to mock the UserService in the controller.
 */
@WebMvcTest(UserController.class)
class MockDependencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Mock Deps Ex 1: Test controller with mocked service dependency")
    void testControllerWithMockedService() throws Exception {
        // Arrange - mock the service
        User mockUser = new User(1L, "Mocked User");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        // Act & Assert - call the controller endpoint
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mocked User"));

        // Verify the service was called
        verify(userService).getUserById(1L);
    }

    @Test
    @DisplayName("Mock Deps Ex 1: Test controller handles service returning null")
    void testControllerWithNullFromService() throws Exception {
        when(userService.getUserById(999L)).thenReturn(null);

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isOk());

        verify(userService).getUserById(999L);
    }
}
