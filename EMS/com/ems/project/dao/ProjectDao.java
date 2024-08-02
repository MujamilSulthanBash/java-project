package com.ems.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.ems.exception.EmployeeException;
import com.ems.project.model.Project;

/**
 * This class represents the managing project records 
 */
public class ProjectDao {

    static List<Project> projects = new ArrayList<>();
    
    /**
     * This method is used to create the project
     * 
     * @param project 
     *     - contain project details
     */
    public void createProject(Project project) throws EmployeeException {
        try { 
            projects.add(project);
        } catch (Exception e) {
            throw new EmployeeException("Issue while creating " + project.getProjectName(), e);
        }
    }

    /**
     * This method is used to retrieve the project
     * 
     * @return project 
     *     - contain project details
     */
    public List<Project> retrieveProjects() throws EmployeeException {
        try {
            return projects;
        } catch (Exception e) {
           throw new EmployeeException("Issue while retriving Projects", e);
       }
    }

    /**
     * This method is used to update the project
     * 
     * @param id 
     *     - accept the integer value
     * @param project 
     *     - contain project details
     */
    public void updateProject(int id, Project project) throws EmployeeException { 
        try {
            for (Project updateProject : projects) {
                if (updateProject.getProjectId() == id) {
                    updateProject = project;
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Issue while updating " + project.getProjectName(), e);
        }
    }

    /**
     * This method is used to remove the project
     * 
     * @param id 
     *     - accept the integer value
     */
    public void deleteProject(int id) throws EmployeeException {
        try {
            for (Project deleteProject : projects) {
                if (deleteProject.getProjectId() == id) {
                    deleteProject.setIsActive();
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Issue while deleting " + id, e);
        }
    } 

} 