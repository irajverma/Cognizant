package com.cognizant.springlearn.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Employee {

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @NotNull
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 30, message = "Employee name should be between 1 and 30 characters")
    private String name;

    @NotNull
    @Min(value = 0, message = "Salary should be zero or above")
    private Double salary;

    @NotNull
    private Boolean permanent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Valid
    private Department department;

    private List<@Valid Skill> skills;

    public Employee() {
        LOGGER.debug("Inside Employee Constructor.");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        LOGGER.debug("Inside setId(): {}", id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside setName(): {}", name);
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        LOGGER.debug("Inside setSalary(): {}", salary);
        this.salary = salary;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        LOGGER.debug("Inside setPermanent(): {}", permanent);
        this.permanent = permanent;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        LOGGER.debug("Inside setDateOfBirth(): {}", dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    // Setter that accepts String for XML configuration
    public void setDateOfBirth(String dateOfBirthStr) {
        LOGGER.debug("Inside setDateOfBirth(String): {}", dateOfBirthStr);
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            this.dateOfBirth = sdf.parse(dateOfBirthStr);
        } catch (Exception e) {
            LOGGER.error("Error parsing date: {}", e.getMessage());
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        LOGGER.debug("Inside setDepartment(): {}", department);
        this.department = department;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        LOGGER.debug("Inside setSkills(): {}", skills);
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary
                + ", permanent=" + permanent + ", dateOfBirth=" + dateOfBirth
                + ", department=" + department + ", skills=" + skills + "]";
    }
}
