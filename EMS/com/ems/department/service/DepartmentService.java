package com.ems.department.service;

import java.util.ArrayList;
import java.util.List;

import com.ems.department.dao.DepartmentDao;
import com.ems.department.model.Department;
import com.ems.exception.EmployeeException;

/**
 * This class represent the responsible for maintaining Department details
 */

public class DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDao();

    /**
     * This method is used to create the department
     *
     * @param department
     *     - it contains department details
     */
    public void createDepartment(Department department) throws EmployeeException { 
        departmentDao.createDepartment(department);  
    }

    /**
     * This method is used to retrieve the department
     *
     * @return List<Department>
     *     - it contains department details
     */
    public List<Department> retrieveDepartments() throws EmployeeException {
        List<Department> allDepartments = departmentDao.retrieveDepartments();
        List<Department> departments = new ArrayList<>(); 
        for (Department department : allDepartments) {
            if (department.getIsActive()) {
                departments.add(department);
            }
        }
        return departments; 
    }

    /**
     * This method is used to retrieve all departments
     *
     * @return List<Department>
     *     - it contains employee details
     */
    public List<Department> retrieveAllDepartments() throws EmployeeException {
        List<Department> allDepartments = departmentDao.retrieveDepartments();
        return allDepartments; 
    }

    /**
     * This method is used to retrieve the department by id
     *
     * @param id
     *     - accepts integer value
     * @return department
     *     - it contains department details
     */
    public Department retrieveDepartmentById(int id) throws EmployeeException {
        List<Department> departments = departmentDao.retrieveDepartments();
        Department departmentById = null; 
        for (Department department : departments) {
            if (department.getDepartmentId() == id) {
                departmentById = department;
            } 
        }
        return departmentById;
    }

    /**
     * This method update the Department by id 
     *
     * @param id        
     *     - accept the integer value 
     * @param department 
     *     - contain employee details
     */
    public void updateDepartment(int id, Department updateDepartment) throws EmployeeException {
        Department  department = retrieveDepartmentById(id);
        department.setDepartmentName(updateDepartment.getDepartmentName());
        departmentDao.updateDepartment(id, department);
    }

    /**
     * This method delete the Department by id 
     *
     * @param id        
     *     - accept the integer value
     */
    public void deleteDepartment(int id) throws EmployeeException {
        departmentDao.deleteDepartment(id);
    }

    /**
     * This method check the department is there or not   
     *
     * @return true        
     *     - if department is there else return false
     */
    public boolean isDepartmentActive() throws EmployeeException {
        List<Department> departments = departmentDao.retrieveDepartments();
        if (! departments.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * This method check the Department id there or not   
     *
     * @return true        
     *     - if department id is there else return false
     */
    public boolean isIdActie(int id) throws EmployeeException {
        Department department = retrieveDepartmentById(id);
        if (department != null) {
            return true;
        }
        return false;
    } 
}