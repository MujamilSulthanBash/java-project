package com.i2it.ems.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.helper.DataBaseConnection;
import com.i2it.ems.model.Department;
import com.i2it.ems.model.Employee;
import com.i2it.ems.model.Project;

/**
 * <p>
 * Manages employee records: add, retrieve, update, and delete.
 * </p>
 */
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Override
    public void createEmployee(Employee employee) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "insert into employee (name, date_of_birth, address,"
                            + "isactive) values(?, ?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, employee.getEmployeeName());
            prepareStatement.setDate(2, Date.valueOf(employee.getEmployeeDob()));
            prepareStatement.setString(3, employee.getEmployeeAddress());
            prepareStatement.setBoolean(4, true);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Employee created ");
        } catch (SQLException e) {
            System.out.println("Issue while creating employee " + employee.getEmployeeName());
            throw new DataBaseException("Issue while creating employee " + employee.getEmployeeName());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
    }

    @Override
    public List<Employee> retrieveEmployees() throws DataBaseException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "SELECT * FROM employee left join department ON "
                            + "employee.department_id = department.id where " 
                            + "employee.isactive = 'true'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employee = new Employee();
                Department department = new Department();
                List<Project> projects = new ArrayList<>();
                employee.setEmployeeId(resultSet.getInt(1));
                employee.setEmployeeName(resultSet.getString(2));
                employee.setEmployeeDob(resultSet.getDate(3).toLocalDate());
                employee.setEmployeeAddress(resultSet.getString(4));
                employee.setIsActive(resultSet.getBoolean(5));
                department.setDepartmentId(resultSet.getInt(7));
                department.setDepartmentName(resultSet.getString(8));
                employee.setDepartment(department);
                int id = resultSet.getInt(1);
                String projectQuery = "select name, id from project where id in " 
                                      + "(select project_id from employee_project " 
                                      + "where employee_id = " + id + ")";
                Statement projectStatement = connection.createStatement();
                ResultSet projectResultSet = projectStatement.executeQuery(projectQuery);
                while (projectResultSet.next()) {
                   Project project = new Project();
                   project.setProjectName(projectResultSet.getString(1));
                   project.setProjectId(projectResultSet.getInt(2));
                   projects.add(project); 
                }
                employee.setProject(projects);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new DataBaseException(" Issue while retrive the employees ");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
        return employees;
    }

    @Override
    public void updateEmployee(int id, Employee employee) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "update employee set name = ?, date_of_birth = ?, address = ?,"
                            + "department_id = ? where id = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, employee.getEmployeeName());
            prepareStatement.setDate(2, Date.valueOf(employee.getEmployeeDob()));
            prepareStatement.setString(3, employee.getEmployeeAddress());
            prepareStatement.setInt(4, employee.getDepartment().getDepartmentId());
            prepareStatement.setInt(5, id);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Employee updated ");
        } catch (SQLException e) {
            System.out.println("Issue while updating employee " + employee.getEmployeeName());
            throw new DataBaseException("Issue while updating employee " + employee.getEmployeeName());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
    }

    @Override
    public void deleteEmployee(int id) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "update employee set isActive = 'false' where id = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Employee deleted with id " + id);
        } catch (SQLException e) {
            System.out.println("Issue while deleting employee id  " + id);
            throw new DataBaseException("Issue while deleting employee id  " + id);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
    }

    @Override
    public List<Employee> retrieveEmployeesByDepartment(int id) throws DataBaseException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "select * from employee where isactive = 'true' and department_id = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt(1));
                employee.setEmployeeName(resultSet.getString(2));
                employee.setEmployeeDob(resultSet.getDate(3).toLocalDate());
                employee.setEmployeeAddress(resultSet.getString(4));
                employee.setIsActive(resultSet.getBoolean(5));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(" Issue while retrive the employees by department ");
            throw new DataBaseException(" Issue while retrive the employees by department ");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
        return employees;
    }

    @Override
    public void addEmployeeProject(Employee employee, Project project) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "insert into employee_project (employee_id, project_id) values(?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, employee.getEmployeeId());
            prepareStatement.setInt(2, project.getProjectId());
            prepareStatement.executeUpdate();
            System.out.println("Added sucessfully");
        } catch (SQLException e) {
            System.out.println("Issue while creating employee " + employee.getEmployeeName());
            throw new DataBaseException("Issue while creating employee " + employee.getEmployeeName());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
    }

    @Override
    public List<Employee> retrieveEmployeesByProject(int id) throws DataBaseException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "select * from employee where id in (select employee_id from " 
                           + "employee_project where project_id = ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt(1));
                employee.setEmployeeName(resultSet.getString(2));
                employee.setEmployeeDob(resultSet.getDate(3).toLocalDate());
                employee.setEmployeeAddress(resultSet.getString(4));
                employee.setIsActive(resultSet.getBoolean(5));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(" Issue while retrive the employees by department ");
            throw new DataBaseException(" Issue while retrive the employees by department ");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            } 
        }
        return employees;
    }

}
