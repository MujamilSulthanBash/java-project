package com.i2it.ems.model;

import java.util.ArrayList;
import java.util.List;

import com.i2it.ems.model.Employee;

/**
 * <p>
 * Project is a piece of planned work or activity that is completed over a period of time and 
 * intended to achieve a particular aim. This class represent the Project Details like id and name.
 * </p>
 */
public class Project {
    private int projectId;
    private String projectName;
    private boolean isActive;
    private List<Employee> employees;

    public Project() {
    } 

    public Project(String projectName) {
        this.projectName = projectName;
        this.isActive = true;
    }
    
    public int getProjectId() {
        return this.projectId;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public boolean getIsActive() {
        return this.isActive;
    } 

    public void setProjectId(int id) {
        this.projectId = id;
    }

    public void setProjectName(String name) {
        this.projectName = name;
    }

    public void setIsActive(boolean flag) {
        this.isActive = flag;
    }

    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }

}