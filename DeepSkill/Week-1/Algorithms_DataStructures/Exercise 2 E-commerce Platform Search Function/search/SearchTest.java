package search;

import java.util.Arrays;

public class SearchTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 2: E-commerce Platform Search Function ===");

        // Initialize array of products
        Product[] products = {
            new Product("P105", "Wireless Mouse", "Accessories"),
            new Product("P101", "Gaming Laptop", "Computers"),
            new Product("P103", "Mechanical Keyboard", "Accessories"),
            new Product("P102", "Smartphone", "Electronics"),
            new Product("P104", "Noise Cancelling Headphones", "Audio")
        };

        // 1. Linear Search
        System.out.println("\n--- Testing Linear Search (Unsorted Array) ---");
        String targetId = "P103";
        Product linearResult = SearchAlgorithms.linearSearch(products, targetId);
        System.out.println("Searching for ID: " + targetId);
        System.out.println("Found: " + linearResult);

        Product linearNotFound = SearchAlgorithms.linearSearch(products, "P999");
        System.out.println("Searching for ID: P999\nFound: " + linearNotFound);

        // 2. Binary Search (Requires sorting first)
        System.out.println("\n--- Sorting Array for Binary Search ---");
        Arrays.sort(products);
        for (Product p : products) {
            System.out.println(p);
        }

        System.out.println("\n--- Testing Binary Search (Sorted Array) ---");
        Product binaryResult = SearchAlgorithms.binarySearch(products, targetId);
        System.out.println("Searching for ID: " + targetId);
        System.out.println("Found: " + binaryResult);

        Product binaryNotFound = SearchAlgorithms.binarySearch(products, "P999");
        System.out.println("Searching for ID: P999\nFound: " + binaryNotFound);

        // 3. Complexity Analysis
        System.out.println("\n=== Complexity Analysis and Algorithm Selection ===");
        System.out.println("1. Time Complexity Comparison:");
        System.out.println("   - Linear Search: O(n) average/worst case. It compares each element sequentially.");
        System.out.println("   - Binary Search: O(log n) average/worst case. It repeatedly halves the search space.");
        System.out.println("2. Selection Recommendation:");
        System.out.println("   - For a highly dynamic, small inventory where products are added frequently, Linear Search is simple.");
        System.out.println("   - For a large scale e-commerce search catalog (e.g., thousands or millions of products), Binary Search (or indexing) is essential.");
        System.out.println("   - Although Binary Search requires O(n log n) sorting cost initially, once sorted, many lookups can be done in O(log n) time.");
    }
}
