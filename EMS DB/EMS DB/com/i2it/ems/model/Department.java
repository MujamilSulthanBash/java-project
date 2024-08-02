package com.i2it.ems.model;

import java.util.ArrayList;
import java.util.List;

import com.i2it.ems.model.Employee;

/**
 * <p>
 * Department is a separate part, division, or branch, of a organization or business.
 * This class represent the Department Details like id and name.
 * </p>
 */
public class Department {
    private int departmentId;
    private String departmentName;
    private boolean isActive;
    private List<Employee> employees;

    public Department() {
    } 

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.isActive = true;
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

    public void setIsActive(boolean flag) {
        this.isActive = flag;
    }

    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }

}