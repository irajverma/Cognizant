public class DecoratorTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 5: Decorator Pattern ===");

        // 1. Basic Email Notifier
        System.out.println("--- Scenario 1: Basic Email Notifier ---");
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("System update starting...");

        System.out.println();

        // 2. Email + SMS Notifier
        System.out.println("--- Scenario 2: Email + SMS Notifier ---");
        Notifier smsAndEmailNotifier = new SMSNotifierDecorator(new EmailNotifier());
        smsAndEmailNotifier.send("System update completed successfully!");

        System.out.println();

        // 3. Email + SMS + Slack Notifier
        System.out.println("--- Scenario 3: Email + SMS + Slack Notifier ---");
        Notifier multiChannelNotifier = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier()
                )
        );
        multiChannelNotifier.send("CRITICAL: Server CPU load exceeds 95%!");
    }
}
