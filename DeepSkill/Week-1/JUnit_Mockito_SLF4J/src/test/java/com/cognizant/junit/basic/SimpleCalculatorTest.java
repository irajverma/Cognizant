package com.cognizant.junit.basic;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Basic Exercise 2: Writing Basic JUnit Tests.
 * Tests for the SimpleCalculator class.
 */
class SimpleCalculatorTest {

    private SimpleCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new SimpleCalculator();
    }

    @Test
    @DisplayName("Test addition of two positive numbers")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @DisplayName("Test addition with negative numbers")
    void testAddNegative() {
        assertEquals(-1, calculator.add(2, -3));
    }

    @Test
    @DisplayName("Test subtraction")
    void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3));
    }

    @Test
    @DisplayName("Test multiplication")
    void testMultiply() {
        assertEquals(15, calculator.multiply(3, 5));
    }

    @Test
    @DisplayName("Test division")
    void testDivide() {
        assertEquals(2.5, calculator.divide(5, 2));
    }

    @Test
    @DisplayName("Test division by zero throws ArithmeticException")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }
}
