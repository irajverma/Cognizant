package com.cognizant.spring;

import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;
import com.cognizant.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Mock Dependencies Exercise 2: Mocking a Repository in a Service Test.
 * Uses @Mock and @InjectMocks to mock the UserRepository dependency.
 */
@ExtendWith(MockitoExtension.class)
class MockDependencyServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Mock Deps Ex 2: Test service with mocked repository")
    void testServiceWithMockedRepository() {
        // Arrange
        User mockUser = new User(1L, "Repository User");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Repository User", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("Mock Deps Ex 2: Test service handles missing user from repository")
    void testServiceWithMissingUser() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        User result = userService.getUserById(999L);

        assertNull(result);
        verify(userRepository).findById(999L);
    }

    @Test
    @DisplayName("Mock Deps Ex 2: Test saveUser with mocked repository")
    void testSaveUser() {
        User user = new User(1L, "New User");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("New User", result.getName());
        verify(userRepository).save(user);
    }
}
