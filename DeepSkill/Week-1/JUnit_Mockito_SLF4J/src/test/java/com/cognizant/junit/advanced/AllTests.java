package com.cognizant.junit.advanced;

import com.cognizant.junit.basic.AssertionsTest;
import com.cognizant.junit.basic.SimpleCalculatorTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Advanced JUnit Exercise 2: Test Suites and Categories.
 * Groups related test classes into a single test suite.
 */
@Suite
@SuiteDisplayName("All Tests Suite")
@SelectClasses({
        SimpleCalculatorTest.class,
        AssertionsTest.class,
        EvenCheckerTest.class,
        ExceptionThrowerTest.class,
        PerformanceTesterTest.class
})
class AllTests {
    // This class serves as a test suite runner.
    // JUnit 5 will run all the test classes listed in @SelectClasses.
}
