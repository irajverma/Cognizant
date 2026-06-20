public class DependencyInjectionTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 11: Dependency Injection ===");

        // 1. Instantiate Concrete Repository (Dependency)
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // 2. Inject Dependency into Service (via Constructor)
        CustomerService customerService = new CustomerService(customerRepository);

        // 3. Test Service Logic
        System.out.println("\n--- Querying Customers ---");
        customerService.displayCustomerInfo("C001");
        customerService.displayCustomerInfo("C002");
        customerService.displayCustomerInfo("C999");
    }
}
