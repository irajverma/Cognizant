public class MVCTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 10: MVC Pattern ===");

        // Fetch student record from database (simulated)
        Student model = retrieveStudentFromDatabase();

        // Create a view to write student details on console
        StudentView view = new StudentView();

        // Create Controller
        StudentController controller = new StudentController(model, view);

        // Initial view display
        System.out.println("Initial State:");
        controller.updateView();

        // Update model data via controller
        System.out.println("Updating student grade and name...");
        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("A+");

        // Display updated view
        System.out.println("Updated State:");
        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        return new Student("S101", "John Doe", "A");
    }
}
