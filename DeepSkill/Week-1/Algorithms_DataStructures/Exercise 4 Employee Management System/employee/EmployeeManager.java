package employee;

public class EmployeeManager {
    private Employee[] employees;
    private int size;
    private int capacity;

    public EmployeeManager(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    /**
     * Adds an employee to the array.
     * Time Complexity: O(1) if space is available.
     */
    public boolean addEmployee(Employee employee) {
        if (size >= capacity) {
            System.out.println("Error: Employee registry is full.");
            return false;
        }
        // Check for duplicates
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employee.getEmployeeId())) {
                System.out.println("Error: Employee with ID " + employee.getEmployeeId() + " already exists.");
                return false;
            }
        }
        employees[size] = employee;
        size++;
        System.out.println("Employee added: " + employee.getName());
        return true;
    }

    /**
     * Searches for an employee by employeeId.
     * Time Complexity: O(n) linear search.
     */
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    /**
     * Traverses the registry and prints all employees.
     * Time Complexity: O(n).
     */
    public void traverseEmployees() {
        if (size == 0) {
            System.out.println("Registry is empty.");
            return;
        }
        System.out.println("--- Employee Records (" + size + "/" + capacity + ") ---");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Deletes an employee by employeeId and shifts subsequent elements to fill the gap.
     * Time Complexity: O(n) worst case (deleting first element shifts n-1 items).
     */
    public boolean deleteEmployee(String employeeId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Error: Employee with ID " + employeeId + " not found.");
            return false;
        }

        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            employees[i] = employees[i + 1];
        }
        employees[size - 1] = null; // Clear last reference
        size--;
        System.out.println("Employee with ID " + employeeId + " successfully deleted.");
        return true;
    }
}
