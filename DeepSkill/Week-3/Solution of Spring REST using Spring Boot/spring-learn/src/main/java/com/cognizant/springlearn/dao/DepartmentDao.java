package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.model.Department;

@Component
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    private static ArrayList<Department> DEPARTMENT_LIST;

    /**
     * Constructor reads department list from XML configuration.
     */
    @SuppressWarnings("unchecked")
    public DepartmentDao() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("departmentList");
        LOGGER.debug("Loaded {} departments from XML config", DEPARTMENT_LIST.size());
        LOGGER.info("END");
    }

    /**
     * Returns the list of all departments.
     */
    public List<Department> getAllDepartments() {
        LOGGER.info("START");
        LOGGER.info("END");
        return DEPARTMENT_LIST;
    }
}
