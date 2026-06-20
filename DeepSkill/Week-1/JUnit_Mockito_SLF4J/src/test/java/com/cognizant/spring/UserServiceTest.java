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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Spring Testing Exercise 2: Mocking a Repository in a Service Test.
 * Spring Testing Exercise 6: Test Service Exception Handling.
 * Uses Mockito to mock the UserRepository dependency.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // ================================================================
    // Exercise 2: Mocking a Repository in a Service Test
    // ================================================================

    @Test
    @DisplayName("Exercise 2: Test getUserById returns user when found")
    void testGetUserById() {
        // Arrange
        User mockUser = new User(1L, "John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(1L, result.getId());
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("Exercise 2: Test getUserById returns null when not found")
    void testGetUserByIdNotFound() {
        // Arrange
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(999L);

        // Assert
        assertNull(result);
        verify(userRepository).findById(999L);
    }

    // ================================================================
    // Exercise 6: Test Service Exception Handling
    // ================================================================

    @Test
    @DisplayName("Exercise 6: Test getUserByIdOrThrow throws exception for missing user")
    void testGetUserByIdOrThrowNotFound() {
        // Arrange
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(999L)
        );
        assertEquals("User not found with id: 999", exception.getMessage());
    }

    @Test
    @DisplayName("Exercise 6: Test getUserByIdOrThrow returns user when found")
    void testGetUserByIdOrThrowFound() {
        // Arrange
        User mockUser = new User(1L, "Jane Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserByIdOrThrow(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
    }

    @Test
    @DisplayName("Test saveUser delegates to repository")
    void testSaveUser() {
        // Arrange
        User userToSave = new User(1L, "New User");
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        // Act
        User result = userService.saveUser(userToSave);

        // Assert
        assertNotNull(result);
        assertEquals("New User", result.getName());
        verify(userRepository).save(userToSave);
    }
}
