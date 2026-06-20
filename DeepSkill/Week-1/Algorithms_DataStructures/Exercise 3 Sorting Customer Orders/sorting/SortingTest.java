package sorting;

public class SortingTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 3: Sorting Customer Orders ===");

        // Test Bubble Sort
        Order[] ordersForBubble = {
            new Order("O001", "Alice", 250.50),
            new Order("O002", "Bob", 120.00),
            new Order("O003", "Charlie", 450.00),
            new Order("O004", "David", 90.00),
            new Order("O005", "Emma", 310.20)
        };

        System.out.println("\n--- Before Bubble Sort ---");
        printOrders(ordersForBubble);

        SortAlgorithms.bubbleSort(ordersForBubble);

        System.out.println("\n--- After Bubble Sort ---");
        printOrders(ordersForBubble);

        // Test Quick Sort
        Order[] ordersForQuick = {
            new Order("O001", "Alice", 250.50),
            new Order("O002", "Bob", 120.00),
            new Order("O003", "Charlie", 450.00),
            new Order("O004", "David", 90.00),
            new Order("O005", "Emma", 310.20)
        };

        System.out.println("\n--- Before Quick Sort ---");
        printOrders(ordersForQuick);

        SortAlgorithms.quickSort(ordersForQuick, 0, ordersForQuick.length - 1);

        System.out.println("\n--- After Quick Sort ---");
        printOrders(ordersForQuick);

        // Analysis
        System.out.println("\n=== Complexity Analysis and Discussion ===");
        System.out.println("1. Time Complexity Comparison:");
        System.out.println("   - Bubble Sort: O(n^2) average and worst-case. It utilizes nested loops to compare adjacent elements.");
        System.out.println("   - Quick Sort: O(n log n) average case, O(n^2) worst case. It uses divide-and-conquer strategy.");
        System.out.println("2. Preference:");
        System.out.println("   - Quick Sort is generally preferred because its average time complexity is O(n log n), which is much faster than O(n^2) for large datasets.");
        System.out.println("   - Bubble Sort is rarely used in production due to poor efficiency on larger lists.");
    }

    private static void printOrders(Order[] orders) {
        for (Order o : orders) {
            System.out.println(o);
        }
    }
}
