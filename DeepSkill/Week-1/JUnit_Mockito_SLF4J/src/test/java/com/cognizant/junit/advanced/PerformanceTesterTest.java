package com.cognizant.junit.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced JUnit Exercise 5: Timeout and Performance Testing.
 * Tests that methods complete within specified time limits.
 */
class PerformanceTesterTest {

    private final PerformanceTester tester = new PerformanceTester();

    @Test
    @DisplayName("Test performTask completes within timeout")
    void testPerformTaskWithinTimeout() {
        // This should complete within 2 seconds (task takes ~100ms)
        assertTimeout(Duration.ofSeconds(2), () -> tester.performTask(100));
    }

    @Test
    @DisplayName("Test quickComputation completes within 1 second")
    void testQuickComputationTimeout() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            int result = tester.quickComputation(1000);
            assertEquals(500500, result); // Sum of 1 to 1000
        });
    }

    @Test
    @DisplayName("Test performTask returns before preemptive timeout")
    void testPerformTaskPreemptiveTimeout() {
        // assertTimeoutPreemptively aborts execution if timeout is exceeded
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> tester.performTask(100));
    }

    @Test
    @DisplayName("Test multiple quick operations complete within timeout")
    void testMultipleOperationsTimeout() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            for (int i = 0; i < 100; i++) {
                tester.quickComputation(100);
            }
        });
    }
}
