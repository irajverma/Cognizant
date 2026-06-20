package search;

public class SearchAlgorithms {

    /**
     * Performs a linear search on the array of products by productId.
     * Time Complexity:
     * - Best Case: O(1) - when the target product is the first element.
     * - Worst Case: O(n) - when the target is the last element or not in the array.
     * - Average Case: O(n) - on average, we examine n/2 elements.
     */
    public static Product linearSearch(Product[] products, String targetId) {
        for (Product product : products) {
            if (product != null && product.getProductId().equals(targetId)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Performs a binary search on the sorted array of products by productId.
     * The array MUST be sorted by productId for this algorithm to work.
     * Time Complexity:
     * - Best Case: O(1) - when the middle element is the target.
     * - Worst Case: O(log n) - when the target is at the final split or not present.
     * - Average Case: O(log n).
     */
    public static Product binarySearch(Product[] products, String targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId().compareTo(targetId);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
