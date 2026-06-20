public class Problem_12_MethodOverloading {
    // Overloaded add method for two integers
    public static int add(int a, int b) {
        return a + b;
    }

    // Overloaded add method for two doubles
    public static double add(double a, double b) {
        return a + b;
    }

    // Overloaded add method for three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println("Add 2 integers (5, 10): " + add(5, 10));
        System.out.println("Add 2 doubles (2.5, 3.5): " + add(2.5, 3.5));
        System.out.println("Add 3 integers (1, 2, 3): " + add(1, 2, 3));
    }
}