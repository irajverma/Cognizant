package sorting;

public class SortAlgorithms {

    /**
     * Sorts the orders using Bubble Sort by totalPrice in ascending order.
     * Time Complexity:
     * - Best Case: O(n) - when array is already sorted (with optimized check).
     * - Worst Case: O(n^2) - when array is reverse sorted.
     * - Average Case: O(n^2).
     * Space Complexity: O(1) (In-place).
     */
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j+1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements were swapped in the inner loop, array is sorted
            if (!swapped) break;
        }
    }

    /**
     * Sorts the orders using Quick Sort by totalPrice in ascending order.
     * Time Complexity:
     * - Best/Average Case: O(n log n).
     * - Worst Case: O(n^2) - when pivot selection is poor (e.g. array already sorted and choosing end element).
     * Space Complexity: O(log n) average auxiliary space due to recursion stack.
     */
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); // index of smaller element

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        // Swap orders[i+1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}
