import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

class StudentDAO {
    private String url = "jdbc:sqlite::memory:";

    public void runDemo() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE students (id INT PRIMARY KEY, name VARCHAR(100));");
            
            // Insert using PreparedStatement
            String insertSql = "INSERT INTO students VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setInt(1, 1);
                pstmt.setString(2, "Alice");
                pstmt.executeUpdate();
                System.out.println("Student inserted successfully.");
            }
            
            // Update using PreparedStatement
            String updateSql = "UPDATE students SET name = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setString(1, "Alice Cooper");
                pstmt.setInt(2, 1);
                pstmt.executeUpdate();
                System.out.println("Student updated successfully.");
            }
            
        } catch (Exception e) {
            System.out.println("JDBC Error: " + e.getMessage());
        }
    }
}

public class Problem_32_InsertUpdateJDBC {
    public static void main(String[] args) {
        new StudentDAO().runDemo();
    }
}