package com.i2it.ems.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.i2it.ems.model.Department;
import com.i2it.ems.model.Project;

/**
 * <p>
 * Employee is a person who is hired to work for another or for a business, This class represent 
 * the Employee Details like id, name, date of birth, and address.
 * </p>
 */
public class Employee {
    private int employeeId;
    private String employeeName;
    private LocalDate employeeDob;
    private String employeeAddress;
    private boolean isActive;
    private Department department;
    private List<Project> projects;
    
    public Employee() {
    }

    public Employee(String employeeName, 
                    LocalDate employeeDob, String employeeAddress) {
        this.employeeName = employeeName;
        this.employeeDob = employeeDob;
        this.employeeAddress = employeeAddress;
    }
    
    public int getEmployeeId() {
        return this.employeeId; 
    }

    public String getEmployeeName() {
        return this.employeeName; 
    }
 
    public LocalDate getEmployeeDob() {
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

    public void setEmployeeDob(LocalDate dob) {
        this.employeeDob = dob;
    }

    public void setEmployeeAddress(String address) {
        this.employeeAddress = address;
    }

    public void setIsActive(boolean flag){
        this.isActive = flag;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setProject(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * This method is used to calculate the age of employee 
     *
     * @return String 
     *     - return the age and month
     */
    public String getEmployeeAge() {
        LocalDate localDob = this.employeeDob;
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(localDob, currentDate); 
        return period.getYears() + " Y " + period.getMonths() + " M ".toString();
    }
    
    public String displayProjects() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Project project : this.projects) {
            stringBuilder.append(project.getProjectName() + ", ");
        }
        return stringBuilder.toString();
    }

}