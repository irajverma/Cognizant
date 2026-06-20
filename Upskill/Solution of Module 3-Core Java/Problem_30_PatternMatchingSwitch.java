public class Problem_30_PatternMatchingSwitch {
    // Java 21+ Pattern matching for switch
    public static void testType(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("Object is an Integer: " + i);
            case String s  -> System.out.println("Object is a String: \"" + s + "\"");
            case Double d  -> System.out.println("Object is a Double: " + d);
            case null      -> System.out.println("Object is null");
            default        -> System.out.println("Unknown type: " + obj.toString());
        }
    }

    public static void main(String[] args) {
        testType(10);
        testType("Hello");
        testType(3.14);
        testType(null);
    }
}