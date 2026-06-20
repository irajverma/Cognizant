package com.cognizant.spring;

import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring Testing Exercise 4: Integration Test with Spring Boot.
 * Tests the full flow from controller to database using H2 in-memory database.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Exercise 4: Test full flow - create and retrieve user")
    void testFullFlow() throws Exception {
        // Clean up
        userRepository.deleteAll();

        // Create a user via POST
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Integration Test User\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test User"));

        // Retrieve the user via GET
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Integration Test User"));
    }

    @Test
    @DisplayName("Exercise 4: Test database persistence")
    void testDatabasePersistence() throws Exception {
        // Clean up
        userRepository.deleteAll();

        // Save directly to repository
        User user = new User(2L, "Database User");
        userRepository.save(user);

        // Verify via API
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Database User"));
    }
}
