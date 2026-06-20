package inventory;

public class InventoryTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Inventory Management System ===");
        
        Inventory inventory = new Inventory();

        // 1. Adding products
        System.out.println("\n--- Testing Add Operation (O(1) complexity) ---");
        Product p1 = new Product("P001", "Laptop", 10, 85000.00);
        Product p2 = new Product("P002", "Smartphone", 50, 30000.00);
        Product p3 = new Product("P003", "Headphones", 100, 2500.00);

        inventory.addProduct(p1);
        inventory.addProduct(p2);
        inventory.addProduct(p3);

        inventory.displayProducts();

        // 2. Updating products
        System.out.println("\n--- Testing Update Operation (O(1) complexity) ---");
        p2.setQuantity(45);
        p2.setPrice(28500.00);
        inventory.updateProduct(p2);

        inventory.displayProducts();

        // 3. Deleting products
        System.out.println("\n--- Testing Delete Operation (O(1) complexity) ---");
        inventory.deleteProduct("P003");

        inventory.displayProducts();

        // 4. Complexity Analysis Discussion
        System.out.println("\n=== Complexity Analysis and Optimization ===");
        System.out.println("1. Time Complexity:");
        System.out.println("   - Add: O(1) on average. HashMap uses hashing to map key to bucket index.");
        System.out.println("   - Update: O(1) on average. Retrieves value using key index and replaces it.");
        System.out.println("   - Delete: O(1) on average. Removes mapping of key and value.");
        System.out.println("2. Trade-offs:");
        System.out.println("   - ArrayList: O(n) for update and delete because it requires search and shifting.");
        System.out.println("   - HashMap: Excellent for search/retrieve by ID, but does not maintain sorting order.");
        System.out.println("3. Optimization:");
        System.out.println("   - Using LinkedHashMap if insertion order needs to be preserved.");
        System.out.println("   - Ensuring a good hash function and initial capacity to minimize collisions.");
    }
}
