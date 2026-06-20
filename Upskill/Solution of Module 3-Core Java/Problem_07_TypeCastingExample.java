public class Problem_07_TypeCastingExample {
    public static void main(String[] args) {
        double dVal = 9.99;
        // Narrowing casting (Double to Int)
        int iVal = (int) dVal;
        System.out.println("Original double: " + dVal);
        System.out.println("Casted to int: " + iVal);
        
        int originalInt = 15;
        // Widening casting (Int to Double)
        double castedDouble = originalInt;
        System.out.println("Original int: " + originalInt);
        System.out.println("Casted to double: " + castedDouble);
    }
}