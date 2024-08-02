package com.i2it.ems.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.helper.DataBaseConnection;
import com.i2it.ems.model.Project;

/**
 * <p>
 * Implementation of projectDao interface.
 * </p>
 */
public class ProjectDaoImpl implements ProjectDao {

    @Override
    public void createProject(Project project) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "insert into project (name, isactive) values(?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, project.getProjectName());
            prepareStatement.setBoolean(2, true);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Project created ");
        } catch (SQLException e) {
             System.out.println("Project " + project.getProjectName() + " is already prescented ");
            throw new DataBaseException("Issue while creating Project " + project.getProjectName());
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
    public List<Project> retrieveProjects() throws DataBaseException {
        List<Project> projects = new ArrayList<>();
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "select * from project where isactive = 'true'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Project project = new Project();
                project.setProjectId(resultSet.getInt(1));
                project.setProjectName(resultSet.getString(2));
                project.setIsActive(resultSet.getBoolean(3));
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Issue while retrive the projects ");
            throw new DataBaseException("Issue while retrive the projects ");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { 
                throw new DataBaseException(" Issue while closing ");
            }
        }
        return projects;
    }

    @Override
    public void updateProject(int id, Project project) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "update project set name = ? where id = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, project.getProjectName());
            prepareStatement.setInt(2, id);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Project updated ");
        } catch (SQLException e) {
            System.out.println("Issue while updating Project " + project.getProjectName());
            throw new DataBaseException("Issue while updating Project " + project.getProjectName());
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
    public void deleteProject(int id) throws DataBaseException {
        Connection connection = null;
        try {
            DataBaseConnection instance = DataBaseConnection.getInstance();
            connection = instance.getConnection();
            String query = "update project set isActive = 'false' where id = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            int rows = prepareStatement.executeUpdate();
            System.out.println(rows + " Project deleted with id " + id);
        } catch (SQLException e) {
            System.out.println("Issue while deleting project id  " + id);
            throw new DataBaseException("Issue while deleting project id  " + id);
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
