package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 1: Logging Error Messages and Warning Levels using SLF4J.
 * Demonstrates logging at different severity levels.
 */
public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        // TRACE level - most verbose, for detailed debugging
        logger.trace("This is a trace message - very detailed information");

        // DEBUG level - debugging information
        logger.debug("This is a debug message - useful for debugging");

        // INFO level - informational messages
        logger.info("This is an info message - general application events");

        // WARN level - potentially harmful situations
        logger.warn("This is a warning message - something unexpected happened");

        // ERROR level - error events
        logger.error("This is an error message - something went wrong");

        // Demonstrating logging with exceptions
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("An arithmetic error occurred", e);
        }

        // Demonstrating logging with different warning scenarios
        logger.warn("Database connection pool is running low: {} connections remaining", 2);
        logger.error("Failed to connect to payment gateway after {} retries", 3);
    }
}
