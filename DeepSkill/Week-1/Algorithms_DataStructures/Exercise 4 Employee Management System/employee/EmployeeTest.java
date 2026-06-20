package employee;

public class EmployeeTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 4: Employee Management System ===");

        EmployeeManager em = new EmployeeManager(5);

        // 1. Add employees
        System.out.println("\n--- Testing Add Operation ---");
        em.addEmployee(new Employee("E001", "John Doe", "Software Engineer", 75000.00));
        em.addEmployee(new Employee("E002", "Jane Smith", "Project Manager", 90000.00));
        em.addEmployee(new Employee("E003", "Robert Johnson", "HR Manager", 65000.00));
        em.addEmployee(new Employee("E004", "Emily Davis", "Data Analyst", 70000.00));
        em.addEmployee(new Employee("E005", "Michael Brown", "Tech Lead", 110000.00));
        
        // Attempting to add past capacity
        em.addEmployee(new Employee("E006", "David Wilson", "Intern", 30000.00));

        em.traverseEmployees();

        // 2. Search employee
        System.out.println("\n--- Testing Search Operation ---");
        String searchId = "E003";
        Employee found = em.searchEmployee(searchId);
        System.out.println("Searching for Employee ID: " + searchId);
        System.out.println("Found: " + found);

        Employee notFound = em.searchEmployee("E999");
        System.out.println("Searching for Employee ID: E999\nFound: " + notFound);

        // 3. Delete employee
        System.out.println("\n--- Testing Delete Operation (with array shifting) ---");
        em.deleteEmployee("E002"); // Should shift E003, E004, E005

        em.traverseEmployees();

        // 4. Complexity Analysis
        System.out.println("\n=== Complexity Analysis and Discussion ===");
        System.out.println("1. Time Complexity of Operations:");
        System.out.println("   - Add: O(1) time complexity if insertion is at the end of the array and capacity is known.");
        System.out.println("   - Search: O(n) average/worst case. Must check each slot sequentially.");
        System.out.println("   - Traverse: O(n) time complexity. Must iterate over the active size.");
        System.out.println("   - Delete: O(n) average/worst case. Requires shifting elements to fill the gap.");
        System.out.println("2. Limitations of Arrays:");
        System.out.println("   - Fixed Size: Cannot grow dynamically without creating a new array and copying elements (O(n) cost).");
        System.out.println("   - Costly Deletion/Insertion: Deleting or inserting at random positions requires shifting elements (O(n)).");
        System.out.println("3. When to Use Arrays:");
        System.out.println("   - When the maximum number of elements is known beforehand and stays stable.");
        System.out.println("   - When fast random access (index-based lookup) in O(1) time is highly critical.");
    }
}
