package com.cognizant.junit.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced JUnit Exercise 1: Parameterized Tests.
 * Tests the EvenChecker class with multiple inputs using parameterized tests.
 */
class EvenCheckerTest {

    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 100, 0, -2, -4})
    @DisplayName("Test even numbers return true")
    void testIsEvenWithEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number), number + " should be even");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 101, -1, -3})
    @DisplayName("Test odd numbers return false")
    void testIsEvenWithOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number), number + " should be odd");
    }

    @ParameterizedTest
    @CsvSource({
            "2, true",
            "3, false",
            "0, true",
            "-1, false",
            "100, true",
            "99, false"
    })
    @DisplayName("Test isEven with CsvSource for expected results")
    void testIsEvenWithCsvSource(int number, boolean expected) {
        assertEquals(expected, evenChecker.isEven(number));
    }
}
