public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        // Simulated database search
        if ("C001".equals(id)) {
            return "Customer: John Doe (email: john.doe@example.com)";
        } else if ("C002".equals(id)) {
            return "Customer: Jane Smith (email: jane.smith@example.com)";
        }
        return "Customer with ID " + id + " not found.";
    }
}
