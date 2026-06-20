import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Record defined for Person (Java 16+)
record Person(String name, int age) {}

public class Problem_29_Records {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Bob", 17);
        Person p3 = new Person("Charlie", 30);
        
        List<Person> list = Arrays.asList(p1, p2, p3);
        
        // Filter based on age >= 18 using Streams
        List<Person> adults = list.stream()
                                  .filter(p -> p.age() >= 18)
                                  .collect(Collectors.toList());
                                  
        System.out.println("Adults (Filtered): " + adults);
    }
}