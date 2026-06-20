import java.util.Scanner;

public class Problem_14_ArraySumAndAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int count = scanner.nextInt();
        
        int[] arr = new int[count];
        double sum = 0;
        
        System.out.println("Enter elements:");
        for (int i = 0; i < count; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];
        }
        
        double avg = (count > 0) ? (sum / count) : 0;
        
        System.out.println("Sum of elements: " + (int)sum);
        System.out.println("Average of elements: " + avg);
        scanner.close();
    }
}