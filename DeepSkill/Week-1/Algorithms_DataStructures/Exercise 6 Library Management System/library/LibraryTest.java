package library;

import java.util.Arrays;

public class LibraryTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 6: Library Management System ===");

        Book[] books = {
            new Book("B003", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B001", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B004", "1984", "George Orwell"),
            new Book("B002", "Moby Dick", "Herman Melville"),
            new Book("B005", "Pride and Prejudice", "Jane Austen")
        };

        // 1. Linear Search (Unsorted or sorted array)
        System.out.println("\n--- Testing Linear Search ---");
        String targetTitle = "1984";
        System.out.println("Searching for title: " + targetTitle);
        Book foundLinear = Library.linearSearchByTitle(books, targetTitle);
        System.out.println("Found: " + foundLinear);

        // 2. Binary Search (Requires sorting by title)
        System.out.println("\n--- Sorting Books by Title for Binary Search ---");
        Arrays.sort(books);
        for (Book b : books) {
            System.out.println(b);
        }

        System.out.println("\n--- Testing Binary Search ---");
        System.out.println("Searching for title: " + targetTitle);
        Book foundBinary = Library.binarySearchByTitle(books, targetTitle);
        System.out.println("Found: " + foundBinary);

        // Searching for non-existent title
        System.out.println("\nSearching for title: The Hobbit");
        Book notFound = Library.binarySearchByTitle(books, "The Hobbit");
        System.out.println("Found: " + notFound);

        // 3. Complexity Analysis
        System.out.println("\n=== Complexity Analysis and Discussion ===");
        System.out.println("1. Time Complexity Comparison:");
        System.out.println("   - Linear Search: O(n) worst/average case. Scans every book in the collection.");
        System.out.println("   - Binary Search: O(log n) worst/average case. Halves search range at each step.");
        System.out.println("2. Selection Guide:");
        System.out.println("   - Linear search is best for small, unsorted datasets or when the list changes too frequently to maintain sort order.");
        System.out.println("   - Binary search is highly preferred for large collections where books are sorted once (e.g. by catalog index) and searched repeatedly.");
    }
}
