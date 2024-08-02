package com.ems.employee.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

import com.ems.department.model.Department;
import com.ems.project.model.Project;

/**
 * <p>
 * This class represent the Employee Details like id, name, date of birth, address, 
 * and belonges to which department and which projects they are
 * </p>
 */
public class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeDob;
    private String employeeAddress;
    private boolean isActive = true;
    private Department department;
    private List<Project> projects;

    public Employee(int employeeId, String employeeName, 
                    String employeeDob, String employeeAddress, 
                    Department department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDob = employeeDob;
        this.employeeAddress = employeeAddress;
        this.department = department;
        projects = new ArrayList<Project>();
    }
    
    public int getEmployeeId() {
        return this.employeeId; 
    }

    public String getEmployeeName() {
        return this.employeeName; 
    }
 
    public String getEmployeeDob() {
        return this.employeeDob; 
    }

    public String getEmployeeAddress() {
        return this.employeeAddress; 
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public Department getDepartment() {
        return this.department;
    }
   
    public List<Project> getProjects() {
        return this.projects;
    }

    public void setEmployeeId(int id) {
        this.employeeId = id;
    }

    public void setEmployeeName(String name) {
        this.employeeName = name;
    }

    public void setEmployeeDob(String dob) {
        this.employeeDob = dob;
    }

    public void setEmployeeAddress(String address) {
        this.employeeAddress = address;
    }

    public void setIsActive(){
        this.isActive = false;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public String getEmployeeAge() {
        String dob = this.employeeDob;
        LocalDate localDob = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(localDob, currentDate); 
        return period.getYears() + "Y " + period.getMonths() + "M ".toString();
    }

}