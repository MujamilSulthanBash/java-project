package com.ems.project.model;

import java.util.ArrayList;
import java.util.List;

import com.ems.employee.model.Employee;

/**
 * This class represent the Project Details
 */
public class Project {

    private int projectId;
    private String projectName;
    private boolean isActive;
    private List<Employee> employees = new ArrayList<>();
 
    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.isActive = true;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public boolean getIsActive() {
        return this.isActive;
    }
  
    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setProjectId(int id) {
        this.projectId = id;
    }

    public void setProjectName(String name) {
        this.projectName = name;
    }

    public void setIsActive() {
        this.isActive = false;
    } 

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

}