package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Component
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static ArrayList<Employee> EMPLOYEE_LIST;

    /**
     * Constructor reads employee list from XML configuration.
     */
    @SuppressWarnings("unchecked")
    public EmployeeDao() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList");
        LOGGER.debug("Loaded {} employees from XML config", EMPLOYEE_LIST.size());
        LOGGER.info("END");
    }

    /**
     * Returns the list of all employees.
     */
    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        LOGGER.info("END");
        return EMPLOYEE_LIST;
    }

    /**
     * Updates an existing employee in the list.
     * Finds employee by ID and replaces it.
     *
     * @throws EmployeeNotFoundException if employee ID not found
     */
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START");
        boolean found = false;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                found = true;
                LOGGER.debug("Updated employee: {}", employee);
                break;
            }
        }
        if (!found) {
            LOGGER.error("Employee not found with id: {}", employee.getId());
            throw new EmployeeNotFoundException("Employee not found with id: " + employee.getId());
        }
        LOGGER.info("END");
    }

    /**
     * Deletes an employee from the list by ID.
     *
     * @throws EmployeeNotFoundException if employee ID not found
     */
    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        boolean removed = EMPLOYEE_LIST.removeIf(emp -> emp.getId().equals(id));
        if (!removed) {
            LOGGER.error("Employee not found with id: {}", id);
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        LOGGER.debug("Deleted employee with id: {}", id);
        LOGGER.info("END");
    }
}
