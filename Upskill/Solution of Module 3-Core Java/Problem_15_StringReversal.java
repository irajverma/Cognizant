import java.util.Scanner;

public class Problem_15_StringReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to reverse: ");
        String str = scanner.nextLine();
        
        // Using StringBuilder reverse helper
        StringBuilder sb = new StringBuilder(str);
        String reversed = sb.reverse().toString();
        
        System.out.println("Reversed String: " + reversed);
        scanner.close();
    }
}