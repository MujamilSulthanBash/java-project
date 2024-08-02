package com.ems.department.model;

import java.util.ArrayList;
import java.util.List;

import com.ems.employee.model.Employee;

/**
 * This class represent the Department Details 
 */
public class Department {

    private int departmentId;
    private String departmentName;
    private boolean isActive = true;
    private List<Employee> employees;

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        employees = new ArrayList<>();
    }
    
    public int getDepartmentId() {
        return this.departmentId;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public boolean getIsActive() {
        return this.isActive;
    } 

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setDepartmentId(int id) {
        this.departmentId = id;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    public void setIsActive() {
        this.isActive = false;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

}