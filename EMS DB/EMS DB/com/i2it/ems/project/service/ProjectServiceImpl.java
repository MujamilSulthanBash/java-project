package com.i2it.ems.project.service;

import java.util.ArrayList;
import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Project;
import com.i2it.ems.project.dao.ProjectDao;
import com.i2it.ems.project.dao.ProjectDaoImpl;
import com.i2it.ems.project.service.ProjectService;

/**
 * <p>
 * Implementation of projectService interface.
 * </p>
 */
public class ProjectServiceImpl implements ProjectService {
    
    private ProjectDao projectDao = new ProjectDaoImpl();
    
    @Override
    public void createProject(Project project) throws DataBaseException { 
        projectDao.createProject(project);
    }

    @Override
    public List<Project> retrieveProjects() throws DataBaseException {
        List<Project> allProjects = projectDao.retrieveProjects();
        if (allProjects.isEmpty()) {
            return null;
        }
        return allProjects;
    }

    @Override
    public Project retrieveProjectById(int id) throws DataBaseException {
        Project getProject = null;
        List<Project> projects = projectDao.retrieveProjects();
        for (Project project : projects) {
            if (project.getProjectId() == id) {
                getProject = project;
            } 
        }
        return getProject;
    }

    @Override
    public void updateProject(int id, Project updateProject) throws DataBaseException {
        Project project = retrieveProjectById(id); 
        projectDao.updateProject(id, updateProject);
    }

    @Override
    public void deleteProject(int id) throws DataBaseException {
        Project project = retrieveProjectById(id);
        projectDao.deleteProject(id);
    }
}
