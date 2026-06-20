package taskmgmt;

public class TaskTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 5: Task Management System ===");

        SinglyLinkedList list = new SinglyLinkedList();

        // 1. Add tasks
        System.out.println("\n--- Testing Add Operation ---");
        list.addTask(new Task("T001", "Design Database Schema", "Done"));
        list.addTask(new Task("T002", "Implement API endpoints", "In Progress"));
        list.addTask(new Task("T003", "Write unit tests", "Pending"));
        list.addTask(new Task("T004", "Configure CI/CD pipelines", "Pending"));

        list.traverseTasks();

        // 2. Search task
        System.out.println("\n--- Testing Search Operation ---");
        String searchId = "T002";
        Task found = list.searchTask(searchId);
        System.out.println("Searching for Task ID: " + searchId);
        System.out.println("Found: " + found);

        Task notFound = list.searchTask("T999");
        System.out.println("Searching for Task ID: T999\nFound: " + notFound);

        // 3. Delete task
        System.out.println("\n--- Testing Delete Operation ---");
        list.deleteTask("T001"); // Delete head
        list.deleteTask("T003"); // Delete middle
        
        list.traverseTasks();

        // 4. Complexity Analysis
        System.out.println("\n=== Complexity Analysis and Discussion ===");
        System.out.println("1. Time Complexity of Operations:");
        System.out.println("   - Add: O(n) as we traverse to the end. (Could be optimized to O(1) with a tail pointer).");
        System.out.println("   - Search: O(n) average and worst-case. Requires walking through node references.");
        System.out.println("   - Traverse: O(n). Visits each node exactly once.");
        System.out.println("   - Delete: O(n) average and worst-case. Needs to find the node and its predecessor.");
        System.out.println("2. Advantages of Linked Lists over Arrays:");
        System.out.println("   - Dynamic Size: No preallocated capacity is needed. Memory is allocated as nodes are added.");
        System.out.println("   - Easy Insertion/Deletion: Once target is found, node insertion or deletion is O(1) without shifting elements.");
        System.out.println("3. Disadvantages:");
        System.out.println("   - Extra Memory: Each node requires extra space for references/pointers.");
        System.out.println("   - No Random Access: To get the i-th element, we must traverse from head (O(n) cost).");
    }
}
