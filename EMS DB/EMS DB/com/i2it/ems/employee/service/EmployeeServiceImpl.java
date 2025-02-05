package com.i2it.ems.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.department.service.DepartmentService;
import com.i2it.ems.department.service.DepartmentServiceImpl;
import com.i2it.ems.employee.dao.EmployeeDao;
import com.i2it.ems.employee.dao.EmployeeDaoImpl;
import com.i2it.ems.employee.service.EmployeeService;
import com.i2it.ems.model.Department;
import com.i2it.ems.model.Employee;
import com.i2it.ems.model.Project;
import com.i2it.ems.project.service.ProjectService;
import com.i2it.ems.project.service.ProjectServiceImpl;


/**
 * <p>
 * Implementation of EmployeeService interface.
 * </p>
 */
public class EmployeeServiceImpl implements EmployeeService {
    
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();
    
    @Override
    public void createEmployee(Employee employee) throws DataBaseException {
        employeeDao.createEmployee(employee);
    }

    @Override
    public List<Employee> retrieveEmployees() throws DataBaseException {
        List<Employee> allEmployees = employeeDao.retrieveEmployees();
        if (allEmployees.isEmpty()) {
            return null;
        }
        return allEmployees;
    }

    @Override
    public Employee retrieveEmployeeById(int id) throws DataBaseException {
        Employee getEmployee = null;
        List<Employee> employees = employeeDao.retrieveEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                getEmployee = employee;
            } 
        }
        return getEmployee;
    }

    @Override
    public void updateEmployee(int id, Employee updateEmployee) throws DataBaseException {
        Employee employee = retrieveEmployeeById(id);
        employeeDao.updateEmployee(id, updateEmployee);
    }

    @Override
    public void deleteEmployee(int id) throws DataBaseException {
        Employee employee = retrieveEmployeeById(id);
        employeeDao.deleteEmployee(id);
    }

    @Override
    public Department retrieveDepartmentById(int id) throws DataBaseException {
        return departmentService.retrieveDepartmentById(id);
    }

    @Override
    public List<Employee> retrieveEmployeesByDepartment(int id) throws DataBaseException {
        List<Employee> allEmployees = employeeDao.retrieveEmployeesByDepartment(id);
        if (allEmployees.isEmpty()) {
            return null;
        }
        return allEmployees;
    }

    @Override
    public Project retrieveProjectById(int id) throws DataBaseException {
        return projectService.retrieveProjectById(id);
    }

    @Override
    public void addEmployeeProject(Employee employee, Project project) throws DataBaseException {
        employeeDao.addEmployeeProject(employee, project);
    }

    @Override
    public List<Employee> retrieveEmployeesByProject(int id) throws DataBaseException {
        List<Employee> allEmployees = employeeDao.retrieveEmployeesByProject(id);
        if (allEmployees.isEmpty()) {
            return null;
        }
        return allEmployees;
    }
    
}
