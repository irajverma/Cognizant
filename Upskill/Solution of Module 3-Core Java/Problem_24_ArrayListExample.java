import java.util.ArrayList;
import java.util.Scanner;

public class Problem_24_ArrayListExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> students = new ArrayList<>();
        
        System.out.println("Enter student names (type 'done' to finish):");
        while (true) {
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            students.add(name);
        }
        
        System.out.println("Registered Students List:");
        for (String name : students) {
            System.out.println("- " + name);
        }
        scanner.close();
    }
}