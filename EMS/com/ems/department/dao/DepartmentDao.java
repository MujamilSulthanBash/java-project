package com.ems.department.dao;

import java.util.ArrayList;
import java.util.List;

import com.ems.department.model.Department;
import com.ems.exception.EmployeeException;

/**
 * This class represents the managing Department records
 */
public class DepartmentDao {

    static List<Department> departments = new ArrayList<>();
    
    /**
     * This method is used to create the department
     * 
     * @param department 
     *     - contain department details
     */
    public void createDepartment(Department department) throws EmployeeException { 
        try {
            departments.add(department);
        } catch (Exception e) {
            throw new EmployeeException("Issue while creating " + department.getDepartmentName(), e);
        }
    }

    /**
     * This method is used to retrieve the department
     * 
     * @return department 
     *     - contain department details
     */
    public List<Department> retrieveDepartments() throws EmployeeException {
        try {
            return departments;
        } catch (Exception e) {
            throw new EmployeeException("Issue while retriving Departments", e);
        }
    }

    /**
     * This method is used to update the department
     * 
     * @param id 
     *     - accept the integer value
     * @param department 
     *     - contain department details
     */
    public void updateDepartment(int id, Department department) throws EmployeeException {
        try {
            for (Department updateDepartment : departments) {
                if (updateDepartment.getDepartmentId() == id) {
                    updateDepartment = department;
                }
           }
        } catch (Exception e) {
            throw new EmployeeException("Issue while updating " + department.getDepartmentName(), e);
        }
    }

    /**
     * This method is used to remove the department
     * 
     * @param id 
     *     - accept the integer value
     */
    public void deleteDepartment(int id) throws EmployeeException {
        try {
            for (Department updateDepartment : departments) {
                if (updateDepartment.getDepartmentId() == id) {
                    updateDepartment.setIsActive();
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Issue while deleting " + id, e);
        } 
    } 

}