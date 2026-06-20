package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Derived query method to find permanent employees
    List<Employee> findByPermanent(boolean permanent);

    // Optimized HQL Join Fetch query (Hands-on 2 in Document 3)
    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department d LEFT JOIN FETCH e.skillList WHERE e.permanent = :perm")
    List<Employee> getAllPermanentEmployeesWithDetails(@Param("perm") boolean permanent);

    // HQL query for Average Salary (Hands-on 4 in Document 3)
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    Double getAverageSalary(@Param("id") int id);

    // Native query (Hands-on 5 in Document 3)
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
