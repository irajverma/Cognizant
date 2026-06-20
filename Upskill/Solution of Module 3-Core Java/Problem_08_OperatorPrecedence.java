public class Problem_08_OperatorPrecedence {
    public static void main(String[] args) {
        int a = 10, b = 5, c = 2;
        
        // Expression demonstrating multiplication takes precedence over addition
        int result1 = a + b * c; // Expected: 10 + (5 * 2) = 20
        System.out.println("Expression: 10 + 5 * 2 = " + result1);
        
        // Parentheses override default precedence
        int result2 = (a + b) * c; // Expected: (10 + 5) * 2 = 30
        System.out.println("Expression: (10 + 5) * 2 = " + result2);
        
        // Logical operator precedence (&& takes precedence over ||)
        boolean expr = true || false && false; // Expected: true || (false && false) = true
        System.out.println("Expression: true || false && false = " + expr);
    }
}