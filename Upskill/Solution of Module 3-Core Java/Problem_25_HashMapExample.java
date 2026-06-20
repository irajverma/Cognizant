import java.util.HashMap;
import java.util.Scanner;

public class Problem_25_HashMapExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> studentMap = new HashMap<>();
        
        // Add sample records
        studentMap.put(101, "Alice");
        studentMap.put(102, "Bob");
        studentMap.put(103, "Charlie");
        
        System.out.print("Enter student ID to retrieve name: ");
        int id = scanner.nextInt();
        
        if (studentMap.containsKey(id)) {
            System.out.println("Student Name: " + studentMap.get(id));
        } else {
            System.out.println("Student ID " + id + " not found.");
        }
        scanner.close();
    }
}