package forecasting;

public class FinancialForecasting {

    /**
     * Calculates future value using a recursive approach.
     * Formula: FV = PV * (1 + r)^n
     * Recursive relation: FV(n) = FV(n-1) * (1 + r)
     * Base case: FV(0) = PV
     * 
     * Time Complexity: O(n) where n is the number of periods (years).
     * Space Complexity: O(n) due to recursion stack frames.
     */
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        // Base case: 0 periods remaining, return present value
        if (periods <= 0) {
            return presentValue;
        }
        // Recursive call: decrease period by 1 and grow present value by rate
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    /**
     * Optimized iterative approach to calculate future value.
     * Prevents stack overflow errors for extremely large period values.
     * 
     * Time Complexity: O(n).
     * Space Complexity: O(1) auxiliary space.
     */
    public static double calculateFutureValueIterative(double presentValue, double growthRate, int periods) {
        double futureValue = presentValue;
        for (int i = 0; i < periods; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }
}
