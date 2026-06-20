package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Using Different Appenders with SLF4J.
 * This class demonstrates logging that goes to both console and file appenders.
 * The appender configuration is defined in logback.xml.
 */
public class AppenderExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        logger.info("=== Application Started ===");

        // Demonstrate different log levels - all will be captured by appenders
        logger.debug("Debug: Application initialization in progress...");
        logger.info("Info: Application configured with console and file appenders");
        logger.warn("Warn: This is a sample warning message");
        logger.error("Error: This is a sample error message");

        // Simulate application workflow
        logger.info("Processing batch job #1001");
        logger.debug("Batch job details: records=500, type=DAILY");

        try {
            simulateWork();
        } catch (Exception e) {
            logger.error("Batch job failed", e);
        }

        logger.info("=== Application Finished ===");
        logger.info("Check app.log file for file-appended logs");
    }

    private static void simulateWork() {
        logger.debug("Starting work simulation...");
        for (int i = 1; i <= 5; i++) {
            logger.info("Processing record {}/5", i);
        }
        logger.debug("Work simulation completed.");
    }
}
