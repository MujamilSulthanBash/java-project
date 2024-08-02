package com.ems.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.ems.department.model.Department;
import com.ems.department.service.DepartmentService;
import com.ems.employee.dao.EmployeeDao;
import com.ems.employee.model.Employee;
import com.ems.exception.EmployeeException;
import com.ems.project.model.Project;
import com.ems.project.service.ProjectService;

/**
 * This class represent the responsible for maintaining Employee details
 */
public class EmployeeService {
 
    private EmployeeDao employeeDao = new EmployeeDao();
    private DepartmentService departmentService = new DepartmentService();
    private ProjectService projectService = new ProjectService();
 
    /**
     * This method is used to create the employee
     *
     * @param employee
     *     - it contains employee details
     */
    public void createEmployee(Employee employee) throws EmployeeException {
        employee.getDepartment().addEmployee(employee); 
        employeeDao.createEmployee(employee);
    }

    /**
     * This method is used to retrieve the employees
     *
     * @return List<Employee>
     *     - it contains employee details
     */
    public List<Employee> retrieveEmployees() throws EmployeeException {
        List<Employee> allEmployees = employeeDao.retrieveEmployees();
        List<Employee> employees = new ArrayList<>(); 
        for (Employee employee : allEmployees) {
            if (employee.getIsActive()) {
                employees.add(employee);
            }
        }
        return employees; 
    }

    /**
     * This method is used to retrieve all employees
     *
     * @return List<Employee>
     *     - it contains employee details
     */
    public List<Employee> retrieveAllEmployees() throws EmployeeException {
        List<Employee> allEmployees = employeeDao.retrieveEmployees();
        return allEmployees; 
    }

    /**
     * This method is used to read the employee by id
     *
     * @param id
     *     - accepts integer value
     * @return employee
     *     - it contains employee details
     */
    public Employee retrieveEmployeeById(int id) throws EmployeeException {
        List<Employee> employees = employeeDao.retrieveEmployees();
        Employee employeeById = null; 
        for (Employee employe : employees) {
            if (employe.getEmployeeId() == id) {
                employeeById = employe;
            } 
        }
        return employeeById;
    }

    /**
     * This method update the Employee by id 
     *
     * @param id        
     *     - accept the integer value 
     * @param employee 
     *     - contain employee details
     */
    public void updateEmployee(int id, Employee updateEmployee) throws EmployeeException {
        Employee  employee = retrieveEmployeeById(id);
        employee.setEmployeeName(updateEmployee.getEmployeeName());
        employee.setEmployeeDob(updateEmployee.getEmployeeDob());
        employee.setEmployeeAddress(updateEmployee.getEmployeeAddress());
        employeeDao.updateEmployee(id, employee);
    }

    /**
     * This method delete the Employee by id 
     *
     * @param id        
     *     - accept the integer value
     */
    public void deleteEmployee(int id) throws EmployeeException {
        employeeDao.deleteEmployee(id);
    }

    /**
     * This method check the employee is there or not   
     *
     * @return true        
     *     - if employee is there else return false
     */
    public boolean isEmployeeActive() throws EmployeeException {
        List<Employee> employees = employeeDao.retrieveEmployees();
        if (employees != null) {
            return true;
        }
        return false;
    }

    /**
     * This method check the employee id there or not   
     *
     * @return true        
     *     - if employee id is there else return false
     */
    public boolean isIdActive(int id) throws EmployeeException {
        Employee  employee = retrieveEmployeeById(id);
        if (employee != null) {
            return true;
        }
        return false;
    }

    /**
     * This class retrieve the departments
     *
     * @return List<Department>
     *     - contains department details
     */
    public List<Department> retrieveDepartments() throws EmployeeException {
        return departmentService.retrieveDepartments();
    }

    /**
     * This class retrieve the department by id
     *
     * @param id 
     *     - accept integer value
     * @return List<Department>
     *     - contains department details
     */
    public Department retrieveDepartmentById(int id) throws EmployeeException {
         return departmentService.retrieveDepartmentById(id);
    } 

    /**
     * This class retrieve the projects
     *
     * @return List<Projects>
     *     - contains project details
     */
    public List<Project> retrieveProjects() throws EmployeeException {
        return projectService.retrieveProjects();
    }

    /**
     * This class retrieve the project by id
     *
     * @param id 
     *     - accept integer value
     * @return List<Project>
     *     - contains dproject details
     */
    public Project retrieveProjectById(int id) throws EmployeeException {
         return projectService.retrieveProjectById(id);
    } 

}