package forecasting;

public class ForecastingTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 7: Financial Forecasting ===");

        double presentValue = 1000.00; // Initial investment
        double annualGrowthRate = 0.05; // 5% growth rate
        int years = 10;

        System.out.println("Initial Investment: $" + presentValue);
        System.out.println("Annual Growth Rate: " + (annualGrowthRate * 100) + "%");
        System.out.println("Forecasting Period: " + years + " years");

        // 1. Recursive calculation
        System.out.println("\n--- Testing Recursive Calculation ---");
        double futureValueRecursive = FinancialForecasting.calculateFutureValue(presentValue, annualGrowthRate, years);
        System.out.printf("Predicted Future Value: $%.2f%n", futureValueRecursive);

        // 2. Iterative calculation (Optimized)
        System.out.println("\n--- Testing Iterative Calculation (Optimized) ---");
        double futureValueIterative = FinancialForecasting.calculateFutureValueIterative(presentValue, annualGrowthRate, years);
        System.out.printf("Predicted Future Value: $%.2f%n", futureValueIterative);

        // 3. Complexity Analysis
        System.out.println("\n=== Complexity Analysis and Optimization ===");
        System.out.println("1. Time Complexity of Recursive Solution:");
        System.out.println("   - Time Complexity: O(n) where n is the number of periods/years, since we execute n recursive calls.");
        System.out.println("   - Space Complexity: O(n) because each call adds a stack frame, which can cause StackOverflowError for large values of n (e.g. n = 10000).");
        System.out.println("2. Optimization:");
        System.out.println("   - Stack Optimization: The iterative solution runs in O(n) time and O(1) space, avoiding stack overhead entirely.");
        System.out.println("   - Fast Exponentiation: Using mathematical calculation like PV * Math.pow(1 + r, n) yields O(log n) efficiency.");
        System.out.println("   - Memoization: In recursive algorithms with overlapping subproblems (e.g., dynamic programming, Fibonacci), caching results reduces exponential O(2^n) time to linear O(n).");
    }
}
