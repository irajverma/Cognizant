package com.cognizant.junit.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Basic Exercise 3: Assertions in JUnit.
 * Demonstrates various JUnit assertion types.
 */
class AssertionsTest {

    @Test
    @DisplayName("Test assertEquals - checks equality")
    void testAssertEquals() {
        assertEquals(5, 2 + 3);
        assertEquals("Hello", "Hello");
        assertEquals(3.14, 3.14, 0.001); // with delta for floating point
    }

    @Test
    @DisplayName("Test assertTrue - checks boolean condition is true")
    void testAssertTrue() {
        assertTrue(5 > 3);
        assertTrue("Hello".startsWith("H"));
    }

    @Test
    @DisplayName("Test assertFalse - checks boolean condition is false")
    void testAssertFalse() {
        assertFalse(5 < 3);
        assertFalse("Hello".isEmpty());
    }

    @Test
    @DisplayName("Test assertNull - checks value is null")
    void testAssertNull() {
        String str = null;
        assertNull(str);
    }

    @Test
    @DisplayName("Test assertNotNull - checks value is not null")
    void testAssertNotNull() {
        assertNotNull(new Object());
        assertNotNull("Hello");
    }

    @Test
    @DisplayName("Test assertThrows - checks exception is thrown")
    void testAssertThrows() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
    }

    @Test
    @DisplayName("Test assertArrayEquals - checks array equality")
    void testAssertArrayEquals() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test assertSame and assertNotSame - checks reference equality")
    void testAssertSameAndNotSame() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        assertSame(str1, str2);       // Same interned reference
        assertNotSame(str1, str3);    // Different reference
    }

    @Test
    @DisplayName("Test assertAll - grouped assertions")
    void testAssertAll() {
        SimpleCalculator calc = new SimpleCalculator();
        assertAll("Calculator operations",
                () -> assertEquals(5, calc.add(2, 3)),
                () -> assertEquals(2, calc.subtract(5, 3)),
                () -> assertEquals(15, calc.multiply(3, 5))
        );
    }
}
