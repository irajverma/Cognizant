package com.cognizant.junit.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced JUnit Exercise 4: Exception Testing.
 * Tests that methods throw expected exceptions.
 */
class ExceptionThrowerTest {

    private final ExceptionThrower thrower = new ExceptionThrower();

    @Test
    @DisplayName("Test throwException throws IllegalArgumentException")
    void testThrowException() {
        assertThrows(IllegalArgumentException.class, () -> thrower.throwException());
    }

    @Test
    @DisplayName("Test throwException message content")
    void testThrowExceptionMessage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> thrower.throwException()
        );
        assertEquals("This is an expected exception", exception.getMessage());
    }

    @Test
    @DisplayName("Test throwExceptionWithMessage with custom message")
    void testThrowExceptionWithMessage() {
        String customMessage = "Custom error message";
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> thrower.throwExceptionWithMessage(customMessage)
        );
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test divideNumbers throws ArithmeticException on division by zero")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> thrower.divideNumbers(10, 0));
    }

    @Test
    @DisplayName("Test divideNumbers works correctly with valid inputs")
    void testDivideValid() {
        assertEquals(5, thrower.divideNumbers(10, 2));
    }
}
