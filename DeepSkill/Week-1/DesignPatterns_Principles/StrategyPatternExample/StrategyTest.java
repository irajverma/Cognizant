public class StrategyTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 8: Strategy Pattern ===");

        PaymentContext context = new PaymentContext();

        // 1. Pay with Credit Card
        System.out.println("\n--- Testing Credit Card Payment Strategy ---");
        PaymentStrategy creditCard = new CreditCardPayment("John Doe", "1234567890123456", "123", "12/28");
        context.setPaymentStrategy(creditCard);
        context.executePayment(520.80);

        // 2. Pay with PayPal
        System.out.println("\n--- Testing PayPal Payment Strategy ---");
        PaymentStrategy payPal = new PayPalPayment("john.doe@example.com", "my_secure_pass");
        context.setPaymentStrategy(payPal);
        context.executePayment(85.50);
    }
}
