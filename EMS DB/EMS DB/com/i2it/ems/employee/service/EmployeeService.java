package com.i2it.ems.employee.service;

import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Department;
import com.i2it.ems.model.Employee;
import com.i2it.ems.model.Project;

/**
 * Managing employee records, add, retrieve, retrieve by id,update and delete.
 */
public interface EmployeeService {

    /**
     * <p>
     * Creates a new employee record.
     * </p>
     *
     * @param employee 
     *     - Employee details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createEmployee(Employee employee) throws DataBaseException; 

    /**
     * <p>
     * Retrieve employees.
     * </p>
     *
     * @return List<Employee> 
     *     - List of employee details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public List<Employee> retrieveEmployees() throws DataBaseException;

    /**
     * <p>
     * Retrieve an employee by their ID.
     * </p>
     *
     * @param id 
     *     - Employee ID
     * @return Employee 
     *     - Employee details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public Employee retrieveEmployeeById(int id) throws DataBaseException;

    /**
     * <p>
     * Updates an employee based on their ID.
     * </p>
     *
     * @param id       
     *     - Employee ID
     * @param employee 
     *     - Updated employee details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updateEmployee(int id, Employee employee) throws DataBaseException;

    /**
     * <p>
     * Deletes an employee based on their ID.
     * </p>
     *
     * @param id 
     *     - Employee ID
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deleteEmployee(int id) throws DataBaseException;

    /**
     * <p>
     * Retrieves an department by their ID.
     * </p>
     *
     * @param id 
     *     - Department ID
     * @return Deartment 
     *     - department details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public Department retrieveDepartmentById(int id) throws DataBaseException;
    
    /**
     * <p>
     * Retrieves employees by department.
     * </p>
     *
     * @param id 
     *     - Department ID
     * @return List<Employee> 
     *     - List of employee details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public List<Employee> retrieveEmployeesByDepartment(int id) throws DataBaseException;

    /**
     * <p>
     * Retrieves an project by their ID.
     * </p>
     *
     * @param id 
     *     - project ID
     * @return Project 
     *     - project details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public Project retrieveProjectById(int id) throws DataBaseException;

    /**
     * <p>
     * Add employee id and project id to the employee_project table.
     * </p>
     *
     * @param employee 
     *     - Employee details
     * @param project 
     *     - Project details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void addEmployeeProject(Employee employee, Project project) throws DataBaseException;

    /**
     * <p>
     * Retrieve employees by project.
     * </p>
     *
     * @param id 
     *     - Project ID
     * @return List<Employee> 
     *     - List of employee details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public List<Employee> retrieveEmployeesByProject(int id) throws DataBaseException;

}