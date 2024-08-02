package com.i2it.ems.department.dao;

import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Department;

/**
 * <p>
 * Manages department records: add, retrieve, update, and delete.
 * </p>
 */
public interface DepartmentDao {

    /**
     * <p>
     * Adds a new department to the records.
     * </p>
     *
     * @param department
     *     - Department details.
     * @throws DataBaseException 
     *     - When Exception occurs.
     */
    public void createDepartment(Department department) throws DataBaseException;
  
    /**
     * <p>
     * Retrieves all department.
     * </p>
     *
     * @retuen List<Department>
     *     - List of Departments detail
     * @throws DataBaseException 
     *     - When Exception occurs.
     */
    public List<Department> retrieveDepartments() throws DataBaseException;

    /**
     * <p>
     * Updates an department based on their ID.
     * </p>
     *
     * @param id
     *     - Department id 
     * @param department
     *     - Department details 
     * @throws DataBaseException 
     *     - When Exception occurs.
     */
    public void updateDepartment(int id, Department department) throws DataBaseException;
     
    /**
     * <p>
     * Delete department based on their ID.
     * </p>
     *
     * @param id
     *     - Department id 
     * @throws DataBaseException 
     *     - When Exception occurs.
     */
    public void deleteDepartment(int id) throws DataBaseException;

}
