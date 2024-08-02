package com.ems.employee.dao;

import java.util.ArrayList;
import java.util.List;

import com.ems.employee.model.Employee;
import com.ems.exception.EmployeeException;

/**
 * This class represents the managing Employee records 
 */
public class EmployeeDao {
    
    static List<Employee> employees = new ArrayList<>();
    
    /**
     * This method is used to create the employee
     * 
     * @param employee 
     *     - contain employee details
     */
    public void createEmployee(Employee employee) throws EmployeeException { 
        try {
            employees.add(employee);
        } catch (Exception e) {
            throw new EmployeeException("Issue while creating " + employee.getEmployeeName(), e);
        }
    }

    /**
     * This method is used to retrieve the employee
     * 
     * @return employee 
     *     - contain employee details
     */
    public List<Employee> retrieveEmployees() throws EmployeeException { 
       try {
            return employees;
       } catch (Exception e) {
           throw new EmployeeException("Issue while retriving Employees", e);
       }
    }

    /**
     * This method is used to update the employee
     * 
     * @param id 
     *     - accept the integer value
     * @param employee 
     *     - contain employee details
     */
    public void updateEmployee(int id, Employee employee) throws EmployeeException {
        try {
            for (Employee updateEmployee : employees) {
                if (updateEmployee.getEmployeeId() == id) {
                    updateEmployee = employee;
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Issue while updating " + employee.getEmployeeName(), e);
        }
    }    

    /**
     * This method is used to remove the employee
     * 
     * @param id 
     *     - accept the integer value
     */
    public void deleteEmployee(int id) throws EmployeeException {
        try {
            for (Employee employee : employees) {
                if (employee.getEmployeeId() == id) {
                    employee.setIsActive();
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Issue while deleting " + id, e);
        }
    }
 
}