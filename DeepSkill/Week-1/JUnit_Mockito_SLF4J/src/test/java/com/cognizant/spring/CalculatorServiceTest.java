package com.cognizant.spring;

import com.cognizant.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring Testing Exercise 1: Basic Unit Test for a Service Method.
 * Tests the CalculatorService add method.
 */
class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    @DisplayName("Test add two positive numbers")
    void testAdd() {
        assertEquals(5, calculatorService.add(2, 3));
    }

    @Test
    @DisplayName("Test add with zero")
    void testAddWithZero() {
        assertEquals(3, calculatorService.add(3, 0));
    }

    @Test
    @DisplayName("Test add negative numbers")
    void testAddNegative() {
        assertEquals(-5, calculatorService.add(-2, -3));
    }

    @Test
    @DisplayName("Test subtract")
    void testSubtract() {
        assertEquals(2, calculatorService.subtract(5, 3));
    }

    @Test
    @DisplayName("Test multiply")
    void testMultiply() {
        assertEquals(15, calculatorService.multiply(3, 5));
    }

    @Test
    @DisplayName("Test divide")
    void testDivide() {
        assertEquals(2.5, calculatorService.divide(5, 2));
    }

    @Test
    @DisplayName("Test divide by zero throws exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(5, 0));
    }
}
