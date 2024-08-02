package com.i2it.ems.department.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Department;
import com.i2it.ems.helper.DataBaseConnection;

/**
 * <p>
 * Implementation of DepartmentDao interface.
 * </p>
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public void createDepartment(Department department) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "insert into department (name, isactive) values(?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, department.getDepartmentName());
            prepareStatement.setBoolean(2, true);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Department created ");
        } catch (SQLException e) {
            System.out.println("Department " + department.getDepartmentName() 
                                + " is already prescented");
            throw new DataBaseException("Issue while creating department " 
                                + department.getDepartmentName());
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
    public List<Department> retrieveDepartments() throws DataBaseException {
        List<Department> departments = new ArrayList<>();
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "select * from department where isactive = 'true'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt(1));
                department.setDepartmentName(resultSet.getString(2));
                department.setIsActive(resultSet.getBoolean(3));
                departments.add(department);
            }
        } catch (SQLException e) {
            System.out.println("Issue while retrive the departments ");
            throw new DataBaseException("Issue while retrive the departments ");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
        return departments;
    }

    @Override
    public void updateDepartment(int id, Department department) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "update department set name = ? where id = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, department.getDepartmentName());
            prepareStatement.setInt(2, id);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Department updated ");
        } catch (SQLException e) {
            System.out.println("Issue while updating department " 
                                + department.getDepartmentName());
            throw new DataBaseException("Issue while updating department " 
                                + department.getDepartmentName());
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
    public void deleteDepartment(int id) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "update department set isActive = 'false' where id = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " department deleted with id " + id);
        } catch (SQLException e) {
            System.out.println("Issue while deleting department id  " + id);
            throw new DataBaseException("Issue while deleting department id  " + id);
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

}
