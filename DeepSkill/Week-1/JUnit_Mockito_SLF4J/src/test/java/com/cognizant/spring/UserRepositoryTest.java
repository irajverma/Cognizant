package com.cognizant.spring;

import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring Testing Exercise 7: Test Custom Repository Query.
 * Tests the custom findByName query method using @DataJpaTest.
 */
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(new User(1L, "John Doe"));
        userRepository.save(new User(2L, "Jane Doe"));
        userRepository.save(new User(3L, "John Doe")); // Duplicate name
        userRepository.save(new User(4L, "Alice Smith"));
    }

    @Test
    @DisplayName("Exercise 7: Test findByName returns matching users")
    void testFindByName() {
        List<User> results = userRepository.findByName("John Doe");

        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(u -> u.getName().equals("John Doe")));
    }

    @Test
    @DisplayName("Exercise 7: Test findByName returns single result")
    void testFindByNameSingleResult() {
        List<User> results = userRepository.findByName("Alice Smith");

        assertEquals(1, results.size());
        assertEquals("Alice Smith", results.get(0).getName());
    }

    @Test
    @DisplayName("Exercise 7: Test findByName returns empty for non-existent name")
    void testFindByNameNotFound() {
        List<User> results = userRepository.findByName("Non Existent");

        assertTrue(results.isEmpty());
    }
}
