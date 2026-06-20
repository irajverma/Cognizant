package com.cognizant.junit.basic;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Basic Exercise 4: Arrange-Act-Assert (AAA) Pattern,
 * Test Fixtures, Setup and Teardown Methods.
 */
class AAAPatternTest {

    private List<String> items;

    @BeforeAll
    static void beforeAll() {
        System.out.println("=== Test Suite Starting ===");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("=== Test Suite Completed ===");
    }

    @BeforeEach
    void setUp() {
        // Setup: Initialize test fixtures before each test
        items = new ArrayList<>();
        items.add("Apple");
        items.add("Banana");
        items.add("Cherry");
        System.out.println("  Setup: Initialized items list with 3 elements");
    }

    @AfterEach
    void tearDown() {
        // Teardown: Clean up after each test
        items.clear();
        items = null;
        System.out.println("  Teardown: Cleaned up items list");
    }

    @Test
    @DisplayName("Test add item using AAA pattern")
    void testAddItem() {
        // Arrange
        String newItem = "Date";

        // Act
        items.add(newItem);

        // Assert
        assertEquals(4, items.size());
        assertTrue(items.contains("Date"));
    }

    @Test
    @DisplayName("Test remove item using AAA pattern")
    void testRemoveItem() {
        // Arrange
        String itemToRemove = "Banana";

        // Act
        boolean removed = items.remove(itemToRemove);

        // Assert
        assertTrue(removed);
        assertEquals(2, items.size());
        assertFalse(items.contains("Banana"));
    }

    @Test
    @DisplayName("Test get item by index using AAA pattern")
    void testGetItem() {
        // Arrange
        int index = 0;

        // Act
        String result = items.get(index);

        // Assert
        assertEquals("Apple", result);
    }

    @Test
    @DisplayName("Test list size after initialization")
    void testInitialSize() {
        // Arrange - handled by @BeforeEach

        // Act
        int size = items.size();

        // Assert
        assertEquals(3, size);
    }
}
