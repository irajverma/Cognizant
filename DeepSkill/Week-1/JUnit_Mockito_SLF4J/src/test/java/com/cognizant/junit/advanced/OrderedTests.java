package com.cognizant.junit.advanced;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced JUnit Exercise 3: Test Execution Order.
 * Demonstrates controlling the order of test execution.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {

    private static StringBuilder executionLog = new StringBuilder();

    @Test
    @Order(1)
    @DisplayName("First test to execute")
    void firstTest() {
        executionLog.append("1 ");
        assertTrue(true, "First test should pass");
    }

    @Test
    @Order(2)
    @DisplayName("Second test to execute")
    void secondTest() {
        executionLog.append("2 ");
        assertEquals(4, 2 + 2);
    }

    @Test
    @Order(3)
    @DisplayName("Third test to execute")
    void thirdTest() {
        executionLog.append("3 ");
        assertNotNull("Not null");
    }

    @Test
    @Order(4)
    @DisplayName("Fourth test to execute - verifies order")
    void fourthTest() {
        executionLog.append("4");
        assertEquals("1 2 3 4", executionLog.toString(),
                "Tests should execute in order 1, 2, 3, 4");
    }
}
