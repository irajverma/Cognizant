package inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Inventory class manages the products in the warehouse.
 * It uses a HashMap to store products, where the key is the productId.
 */
public class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    /**
     * Adds a product to the inventory.
     * Time Complexity: O(1) average case, as HashMap insertion is O(1) on average.
     */
    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists. Use update instead.");
            return;
        }
        products.put(product.getProductId(), product);
        System.out.println("Added: " + product);
    }

    /**
     * Updates an existing product in the inventory.
     * Time Complexity: O(1) average case, as looking up and updating in a HashMap is O(1).
     */
    public void updateProduct(Product product) {
        if (!products.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " does not exist. Use add instead.");
            return;
        }
        products.put(product.getProductId(), product);
        System.out.println("Updated: " + product);
    }

    /**
     * Deletes a product from the inventory by its ID.
     * Time Complexity: O(1) average case, as removal from a HashMap is O(1).
     */
    public void deleteProduct(String productId) {
        if (!products.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " does not exist.");
            return;
        }
        Product removed = products.remove(productId);
        System.out.println("Deleted product with ID " + productId + ": " + removed.getProductName());
    }

    /**
     * Displays all products in the inventory.
     * Time Complexity: O(n), where n is the number of products.
     */
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("--- Current Inventory List ---");
        for (Product product : products.values()) {
            System.out.println(product);
        }
        System.out.println("------------------------------");
    }
}
