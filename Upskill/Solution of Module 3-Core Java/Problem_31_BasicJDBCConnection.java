import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Problem_31_BasicJDBCConnection {
    public static void main(String[] args) {
        // Using in-memory SQLite database for portable demonstration
        String url = "jdbc:sqlite::memory:";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            // Setup table
            stmt.execute("CREATE TABLE students (id INT PRIMARY KEY, name VARCHAR(100));");
            stmt.execute("INSERT INTO students VALUES (1, 'Alice');");
            
            // Execute select query
            ResultSet rs = stmt.executeQuery("SELECT * FROM students;");
            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("JDBC Connection Failed: " + e.getMessage());
        }
    }
}