import java.util.Scanner;

public class Problem_13_RecursiveFibonacci {
    public static long fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter positive integer n: ");
        int n = scanner.nextInt();
        
        System.out.println("Fibonacci number at position " + n + " is: " + fibonacci(n));
        scanner.close();
    }
}