package com.ems.project.service;

import java.util.ArrayList;
import java.util.List;

import com.ems.exception.EmployeeException;
import com.ems.project.dao.ProjectDao;
import com.ems.project.model.Project;

/**
 * This class represent the responsible for maintaining project details
 */
public class ProjectService {

    private ProjectDao projectDao = new ProjectDao();

    /**
     * This method is used to create the project
     *
     * @param project
     *     - it contains project details
     */
    public void createProject(Project project) throws EmployeeException {
        projectDao.createProject(project);  
    }

    /**
     * This method is used to retrieve the project
     *
     * @return List<project>
     *     - it contains project details
     */
    public List<Project> retrieveProjects() throws EmployeeException {
        List<Project> allProjects = projectDao.retrieveProjects();
        List<Project> projects = new ArrayList<>(); 
        for (Project project : allProjects) {
            if (project.getIsActive()) {
                projects.add(project);
            }
        }
        return projects; 
    }

    /**
     * This method is used to retrieve all projects
     *
     * @return List<Projects>
     *     - it contains project details
     */
    public List<Project> retrieveAllprojects() throws EmployeeException {
        List<Project> allProjects = projectDao.retrieveProjects();
        return allProjects; 
    }

    /**
     * This method is used to retrieve the project by id
     *
     * @param id
     *     - accepts integer value
     * @return project
     *     - it contains project details
     */
    public Project retrieveProjectById(int id) throws EmployeeException {
        List<Project> projects = projectDao.retrieveProjects();
        Project projectById = null; 
        for (Project project : projects) {
            if (project.getProjectId() == id) {
                projectById = project;
            } 
        }
        return projectById;
    }

    /**
     * This method update the project by id 
     *
     * @param id        
     *     - accept the integer value 
     * @param project 
     *     - contain project details
     */
    public void updateProject(int id, Project updateProject) throws EmployeeException {
        Project  project = retrieveProjectById(id);
        project.setProjectName(updateProject.getProjectName());
        projectDao.updateProject(id, project);
    }

    /**
     * This method delete the project by id 
     *
     * @param id        
     *     - accept the integer value
     */
    public void deleteProject(int id) throws EmployeeException {
        projectDao.deleteProject(id);
    }

    /**
     * This method check the project is there or not   
     *
     * @return true        
     *     - if project is there else return false
     */
    public boolean isProjectThere() throws EmployeeException {
        List<Project> projects = retrieveAllprojects();
        if (! projects.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * This method check the project id there or not   
     *
     * @return true        
     *     - if project id is there else return false
     */
    public boolean isIdThere(int id) throws EmployeeException {
        Project project = retrieveProjectById(id);
        if (project != null) {
            return true;
        }
        return false;
    }

}