package com.cognizant.spring;

import com.cognizant.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring Testing Exercise 9: Parameterized Test with JUnit.
 * Uses @ParameterizedTest to test the CalculatorService with multiple inputs.
 */
class ParameterizedCalculatorTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "10, 20, 30",
            "-1, -1, -2",
            "0, 0, 0",
            "100, -50, 50",
            "Integer.MAX_VALUE - 1, 1, 2147483647"
    })
    @DisplayName("Exercise 9: Parameterized add test")
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 2",
            "10, 10, 0",
            "0, 5, -5",
            "-3, -3, 0"
    })
    @DisplayName("Exercise 9: Parameterized subtract test")
    void testSubtract(int a, int b, int expected) {
        assertEquals(expected, calculatorService.subtract(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 6",
            "0, 100, 0",
            "-2, 3, -6",
            "7, 7, 49"
    })
    @DisplayName("Exercise 9: Parameterized multiply test")
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, calculatorService.multiply(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 2, 5.0",
            "7, 2, 3.5",
            "100, 4, 25.0",
            "-10, 2, -5.0"
    })
    @DisplayName("Exercise 9: Parameterized divide test")
    void testDivide(int a, int b, double expected) {
        assertEquals(expected, calculatorService.divide(a, b), 0.001);
    }
}
