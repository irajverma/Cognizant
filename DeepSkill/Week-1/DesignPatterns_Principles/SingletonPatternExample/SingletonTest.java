public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Singleton Pattern ===");

        // Get instance of Logger
        Logger logger1 = Logger.getInstance();
        logger1.log("First log message.");

        // Get another instance of Logger
        Logger logger2 = Logger.getInstance();
        logger2.log("Second log message.");

        // Verify both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("\nSUCCESS: Both logger1 and logger2 point to the same instance.");
        } else {
            System.out.println("\nFAILURE: logger1 and logger2 point to different instances.");
        }

        // Print hash codes to verify
        System.out.println("Logger1 Hash Code: " + logger1.hashCode());
        System.out.println("Logger2 Hash Code: " + logger2.hashCode());
    }
}
