import java.util.Scanner;

public class Problem_02_SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        
        System.out.println("Choose operation: +, -, *, /");
        char op = scanner.next().charAt(0);
        
        double result = 0;
        boolean valid = true;
        
        switch (op) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': 
                if (num2 != 0) {
                    result = num1 / num2; 
                } else {
                    System.out.println("Error: Division by zero.");
                    valid = false;
                }
                break;
            default:
                System.out.println("Invalid operation.");
                valid = false;
        }
        
        if (valid) {
            System.out.println("Result: " + result);
        }
        scanner.close();
    }
}