package com.cognizant.junit.advanced;

/**
 * Throws exceptions for Advanced JUnit Exercise 4 (Exception Testing).
 */
public class ExceptionThrower {

    public void throwException() throws IllegalArgumentException {
        throw new IllegalArgumentException("This is an expected exception");
    }

    public void throwExceptionWithMessage(String message) throws RuntimeException {
        throw new RuntimeException(message);
    }

    public int divideNumbers(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}
