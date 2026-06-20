import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem_27_LambdaExpressions {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Strawberry");
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");
        
        System.out.println("Original list: " + list);
        
        // Sort using lambda expression
        Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
        
        System.out.println("Sorted list: " + list);
    }
}