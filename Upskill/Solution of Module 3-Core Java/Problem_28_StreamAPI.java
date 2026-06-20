import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem_28_StreamAPI {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Use Stream API to filter even numbers and collect results
        List<Integer> evens = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
                                     
        System.out.println("Even Numbers filtered using Streams: " + evens);
    }
}