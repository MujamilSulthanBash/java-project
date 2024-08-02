package com.i2it.ems.employee.dao;

import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Employee;
import com.i2it.ems.model.Project;

/**
 * <p>
 * Manage the employee records: add, retrieve, update, and delete.
 * </p>
 */
public interface EmployeeDao {
    
    /**
     * <p>
     * Adds a new employee to the records.
     * </p>
     *
     * @param employee
     *     - Employee details 
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public void createEmployee(Employee employee) throws DataBaseException;

    /**
     * <p>
     * Retrieves all employees.
     * </p>
     * 
     * @return List<Employees>
     *     - List of Employees details
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public List<Employee> retrieveEmployees() throws DataBaseException;

    /**
     * <p>
     * Updates an employee based on their ID.
     * </p>
     *
     * @param id
     *     - Employee id
     * @param employee
     *     - Employee details
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public void updateEmployee(int id, Employee employee) throws DataBaseException;

    /**
     * <p>
     * Deletes an employee based on their ID.
     * </p>
     *
     * @param id
     *     - Employee id
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public void deleteEmployee(int id) throws DataBaseException;

    /**
     * <p>
     * Retrieves all employees by department.
     * </p>
     *
     * @param id
     *     - Department id
     * @return List<Employees>
     *     - List of Employees details
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public List<Employee> retrieveEmployeesByDepartment(int id) throws DataBaseException;

    /**
     * <p>
     * Adds employee id and project id to the records.
     * </p>
     * 
     * @param employee
     *     - Employee details
     * @param project
     *     - Project details
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public void addEmployeeProject(Employee employee, Project project) throws DataBaseException;

    /**
     * <p>
     * Retrieves all employees by project.
     * </p>
     *
     * @param id
     *     - project id
     * @return List<Employees>
     *     - List of Employees details
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public List<Employee> retrieveEmployeesByProject(int id) throws DataBaseException;

}
