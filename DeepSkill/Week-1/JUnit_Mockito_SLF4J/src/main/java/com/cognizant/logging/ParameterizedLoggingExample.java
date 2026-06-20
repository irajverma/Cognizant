package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 2: Parameterized Logging using SLF4J.
 * Demonstrates how to use parameterized log messages for better performance.
 * SLF4J uses {} placeholders which are more efficient than string concatenation
 * because the string is only constructed if the log level is enabled.
 */
public class ParameterizedLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String userName = "John Doe";
        int accountId = 12345;
        double balance = 15000.75;

        // Single parameter
        logger.info("User {} has logged in", userName);

        // Multiple parameters
        logger.info("User {} with Account ID {} has a balance of ${}", userName, accountId, balance);

        // Demonstrating parameterized logging in different scenarios
        String operation = "transfer";
        double amount = 500.00;
        String fromAccount = "ACC-001";
        String toAccount = "ACC-002";

        logger.info("Initiating {} of ${} from {} to {}", operation, amount, fromAccount, toAccount);

        // Logging with computed values (SLF4J defers string construction)
        logger.debug("Processing transaction: user={}, amount={}, timestamp={}",
                userName, amount, System.currentTimeMillis());

        // Logging exceptions with parameters
        try {
            processTransaction(userName, -100);
        } catch (IllegalArgumentException e) {
            logger.error("Transaction failed for user {}: {}", userName, e.getMessage(), e);
        }

        // Conditional logging (efficient - avoids unnecessary computation)
        if (logger.isDebugEnabled()) {
            logger.debug("Detailed transaction info: {}", buildDetailedInfo(userName, accountId));
        }

        logger.info("Parameterized logging demonstration completed");
    }

    private static void processTransaction(String user, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
    }

    private static String buildDetailedInfo(String user, int accountId) {
        // Simulates an expensive string building operation
        return String.format("User: %s, Account: %d, Status: Active", user, accountId);
    }
}
