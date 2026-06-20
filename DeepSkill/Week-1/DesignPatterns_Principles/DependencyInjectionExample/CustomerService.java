public class CustomerService {
    private final CustomerRepository customerRepository;

    // Constructor Injection: The dependency is injected via the constructor
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void displayCustomerInfo(String id) {
        String customerInfo = customerRepository.findCustomerById(id);
        System.out.println("Customer Query ID: " + id);
        System.out.println("Result: " + customerInfo);
    }
}
