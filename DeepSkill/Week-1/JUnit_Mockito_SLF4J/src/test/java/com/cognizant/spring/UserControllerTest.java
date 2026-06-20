package com.cognizant.spring;

import com.cognizant.controller.UserController;
import com.cognizant.model.User;
import com.cognizant.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring Testing Exercise 3: Testing a REST Controller with MockMvc.
 * Spring Testing Exercise 5: Test Controller POST Endpoint.
 * Spring Testing Exercise 8: Test Controller Exception Handling.
 * Tests controller endpoints using MockMvc and MockBean.
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // ================================================================
    // Exercise 3: Testing a REST Controller with MockMvc
    // ================================================================

    @Test
    @DisplayName("Exercise 3: Test GET /users/{id} returns user")
    void testGetUser() throws Exception {
        // Arrange
        User mockUser = new User(1L, "John Doe");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        // Act & Assert
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @DisplayName("Exercise 3: Test GET /users/{id} returns null for missing user")
    void testGetUserNotFound() throws Exception {
        when(userService.getUserById(999L)).thenReturn(null);

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isOk());
    }

    // ================================================================
    // Exercise 5: Test Controller POST Endpoint
    // ================================================================

    @Test
    @DisplayName("Exercise 5: Test POST /users creates user")
    void testCreateUser() throws Exception {
        // Arrange
        User savedUser = new User(1L, "Jane Doe");
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        // Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Jane Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    // ================================================================
    // Exercise 8: Test Controller Exception Handling
    // ================================================================

    @Test
    @DisplayName("Exercise 8: Test exception handler returns 404 for NoSuchElementException")
    void testExceptionHandling() throws Exception {
        when(userService.getUserById(999L))
                .thenThrow(new java.util.NoSuchElementException("User not found"));

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
