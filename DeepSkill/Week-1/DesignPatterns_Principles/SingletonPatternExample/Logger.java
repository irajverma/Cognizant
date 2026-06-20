public class Logger {
    // 1. Private static instance of the same class (volatile to ensure thread safety during double-checked locking)
    private static volatile Logger instance;

    // 2. Private constructor to prevent instantiation from outside the class
    private Logger() {
        // Prevent instantiation via reflection
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        System.out.println("Logger initialized. (This message should only appear ONCE)");
    }

    // 3. Public static method to obtain the single instance (using double-checked locking for thread safety)
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}
