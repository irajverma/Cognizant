package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Employee get(int id) {
        LOGGER.info("Start - get employee id: {}", id);
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start - save employee: {}", employee);
        employeeRepository.save(employee);
        LOGGER.info("End - save employee");
    }

    @Transactional(readOnly = true)
    public List<Employee> getPermanentEmployees() {
        return employeeRepository.findByPermanent(true);
    }

    @Transactional(readOnly = true)
    public List<Employee> getPermanentEmployeesWithDetails() {
        return employeeRepository.getAllPermanentEmployeesWithDetails(true);
    }

    @Transactional(readOnly = true)
    public Double getAverageSalary(int departmentId) {
        return employeeRepository.getAverageSalary(departmentId);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployeesNative() {
        return employeeRepository.getAllEmployeesNative();
    }

    // =========================================================================
    // TODO: Hands-on 6 - Criteria Query (Amazon-like Dynamic Filtering)
    // =========================================================================
    // I was not able to solve this. The CriteriaBuilder, CriteriaQuery, Root API, 
    // and combining dynamic Predicates were too confusing for me. 
    // I will ask the instructor for assistance.
    // =========================================================================
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByFiltersCriteria(String name, Double minSalary, Boolean permanent) {
        // TODO: Could not resolve. Throwing exception to make it clear.
        throw new UnsupportedOperationException(
            "Hands-on 6 (Criteria Query) is not implemented. I couldn't understand how to build dynamic predicates."
        );
    }
}
