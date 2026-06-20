package com.cognizant.junit.advanced;

/**
 * Performs a task for Advanced JUnit Exercise 5 (Timeout/Performance Testing).
 */
public class PerformanceTester {

    /**
     * Simulates a task that takes approximately the given milliseconds to complete.
     */
    public void performTask(long durationMs) {
        try {
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Performs a quick computation (should complete within timeout).
     */
    public int quickComputation(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
