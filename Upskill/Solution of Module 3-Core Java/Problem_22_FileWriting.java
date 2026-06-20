import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Problem_22_FileWriting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to write to output.txt: ");
        String text = scanner.nextLine();
        
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(text);
            System.out.println("Success: Data has been written to output.txt");
        } catch (IOException e) {
            System.out.println("File writing failed: " + e.getMessage());
        }
        scanner.close();
    }
}