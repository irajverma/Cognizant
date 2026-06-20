package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.AttemptService;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    @Autowired
    private CountryService countryService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private AttemptService attemptService;

    public static void main(String[] args) {
        LOGGER.info("Starting OrmLearnApplication...");
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Inside main - Running Spring Data JPA Exercises");

        System.out.println("\n==================================================");
        System.out.println("  RUNNING PART A: COUNTRY CRUD (Hands-on 1-9)      ");
        System.out.println("==================================================");
        testCountryCrud();

        System.out.println("\n==================================================");
        System.out.println("  RUNNING PART B: EMPLOYEE & DEPARTMENT MAPPINGS   ");
        System.out.println("==================================================");
        testEmployeeMappings();

        System.out.println("\n==================================================");
        System.out.println("  RUNNING PART C: QUIZ ATTEMPT (Hands-on 3)       ");
        System.out.println("==================================================");
        testQuizAttempt();

        System.out.println("\n==================================================");
        System.out.println("  ADDITIONAL EXERCISES STATUS                      ");
        System.out.println("==================================================");
        testAdditionalDifficultTasks();
    }

    private void testCountryCrud() {
        try {
            // 1. Get all countries
            List<Country> countries = countryService.getAllCountries();
            System.out.println("Successfully loaded " + countries.size() + " countries.");

            // 2. Find Country by Code (IN)
            Country india = countryService.findCountryByCode("IN");
            System.out.println("Found country IN: " + india.getName());

            // 3. Add a new country
            System.out.println("Adding new country 'XX' - 'Test Country'...");
            Country newCountry = new Country("XX", "Test Country");
            countryService.addCountry(newCountry);
            System.out.println("Added successfully. Verification: " + countryService.findCountryByCode("XX").getName());

            // 4. Update country
            System.out.println("Updating country 'XX' to 'Modified Country'...");
            countryService.updateCountry("XX", "Modified Country");
            System.out.println("Updated successfully. Verification: " + countryService.findCountryByCode("XX").getName());

            // 5. Delete country
            System.out.println("Deleting country 'XX'...");
            countryService.deleteCountry("XX");
            try {
                countryService.findCountryByCode("XX");
            } catch (CountryNotFoundException e) {
                System.out.println("Deleted successfully. Confirmed by: " + e.getMessage());
            }

        } catch (Exception e) {
            LOGGER.error("Error in Country CRUD: ", e);
        }
    }

    private void testEmployeeMappings() {
        try {
            // 1. Get Employee and Department (Many-to-One Eager/Lazy check)
            System.out.println("Fetching Employee ID 1...");
            Employee employee = employeeService.get(1);
            if (employee != null) {
                System.out.println("Employee Name: " + employee.getName());
                System.out.println("Employee Department: " + employee.getDepartment().getName());
            }

            // 2. Add Employee
            System.out.println("\nAdding a new Employee (Mark Smith) to IT department...");
            Department itDept = departmentService.get(1);
            Employee newEmp = new Employee();
            newEmp.setName("Mark Smith");
            newEmp.setSalary(62000.0);
            newEmp.setPermanent(true);
            newEmp.setDateOfBirth(new Date());
            newEmp.setDepartment(itDept);
            employeeService.save(newEmp);
            System.out.println("Employee saved successfully.");

            // 3. Update Employee
            System.out.println("\nUpdating Employee ID 1 department...");
            Employee empToUpdate = employeeService.get(1);
            Department hrDept = departmentService.get(2);
            empToUpdate.setDepartment(hrDept);
            employeeService.save(empToUpdate);
            System.out.println("Employee ID 1 department updated to: " + employeeService.get(1).getDepartment().getName());

            // 4. Get Department Employees List (One-to-Many Lazy check)
            System.out.println("\nFetching HR Department (ID 2) and employees...");
            Department hr = departmentService.get(2);
            System.out.println("Department: " + hr.getName());
            // This triggers lazy load inside transactional scope
            System.out.println("Number of employees: " + hr.getEmployeeList().size());

            // 5. Eager fetch simulation via HQL JOIN FETCH
            System.out.println("\nFetching all permanent employees with Join Fetch query (No Lazy errors):");
            List<Employee> permEmployees = employeeService.getPermanentEmployeesWithDetails();
            permEmployees.forEach(e -> {
                System.out.print(" - " + e.getName() + " works in " + e.getDepartment().getName() + " with skills: ");
                e.getSkillList().forEach(s -> System.out.print(s.getName() + " "));
                System.out.println();
            });

            // 6. Average salary using HQL aggregate (Hands-on 4)
            System.out.println("\nCalculating average salary for IT department (ID 1)...");
            Double avgSalary = employeeService.getAverageSalary(1);
            System.out.println("Average Salary in IT: $" + avgSalary);

            // 7. Native Query (Hands-on 5)
            System.out.println("\nListing all employees using Native SQL Query:");
            List<Employee> allEmployees = employeeService.getAllEmployeesNative();
            allEmployees.forEach(e -> System.out.println(" - " + e.getName() + " ($" + e.getSalary() + ")"));

        } catch (Exception e) {
            LOGGER.error("Error in Employee Mappings: ", e);
        }
    }

    private void testQuizAttempt() {
        // Run the partially implemented attempt details (Hands-on 3)
        attemptService.printAttemptDetails(1, 1);
    }

    private void testAdditionalDifficultTasks() {
        // 1. Hands-on 6 - Criteria Query
        System.out.println("\n[Hands-on 6 - Criteria Query]");
        try {
            employeeService.getEmployeesByFiltersCriteria("John", 50000.0, true);
        } catch (UnsupportedOperationException e) {
            System.out.println("Result: " + e.getMessage());
        }

        // 2. EMS Exercise 9 & 10 (Multiple Datasources & Batch Processing)
        System.out.println("\n[Employee Management System - Exercise 9 & 10]");
        System.out.println("Result: Multiple data sources configuration (Exercise 9) and Hibernate batch processing");
        System.out.println("        with custom Hibernate dialect tuning (Exercise 10) are NOT implemented.");
        System.out.println("        These configurations require complex transaction routing and multi-datasource");
        System.out.println("        plumbing which I was unable to complete. Need help.");
        System.out.println("==================================================");
    }
}
