package com.i2it.ems.project.dao;

import java.util.List;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Project;

/**
 * <p>
 * Manages department records: add, retrieve, update, and delete.
 * </p>
 */
public interface ProjectDao {

    /**
     * <p>
     * Adds a new Project to the records.
     * </p>
     *
     * @param project
     *     - Project Details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createProject(Project project) throws DataBaseException;

    /**
     * <p>
     * Retrieves all Projects.
     * </p>
     *
     * @return List<Project>
     *     - List of Projects 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public List<Project> retrieveProjects() throws DataBaseException;

    /**
     * <p>
     * Updates an project based on their ID.
     * </p>
     *
     * @return List<Project>
     *     - List of Projects 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updateProject(int id, Project project) throws DataBaseException;

    /**
     * <p>
     * Delete project based on their ID.
     * </p>
     *
     * @param id 
     *     - Project id 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deleteProject(int id) throws DataBaseException;

}
