import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Problem_33_TransactionHandlingJDBC {
    public static void main(String[] args) {
        String url = "jdbc:sqlite::memory:";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE accounts (id INT PRIMARY KEY, balance DOUBLE);");
            stmt.execute("INSERT INTO accounts VALUES (1, 1000.0);");
            stmt.execute("INSERT INTO accounts VALUES (2, 500.0);");
            
            // Set autocommit to false (Transaction starts)
            conn.setAutoCommit(false);
            
            try {
                // Subtract debit from account 1
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 100.0 WHERE id = 1;");
                
                // Add credit to account 2
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 100.0 WHERE id = 2;");
                
                // Commit if both succeed
                conn.commit();
                System.out.println("Transaction Committed Successfully!");
            } catch (Exception ex) {
                // Rollback if any error occurs
                conn.rollback();
                System.out.println("Transaction Failed! Rolled back changes.");
            }
            
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}