package com.patient.service;

import java.util.List;

import com.exception.DataBaseException;
import com.model.Patient;

/**
 * <p>
 * Managing patient records, add, retrieve, retrieve by id,update and delete.
 * </p>
 */
public interface ProjectService {

    /**
     * <p>
     * Creates a new patient record.
     * </p>
     *
     * @param patient 
     *     - patient details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createPatient(Patient patient) throws DataBaseException;                      

    /**
     * <p>
     * Retrieves patients.
     * </p>
     *
     * @return List<Patient> 
     *     - List of patient details
     * @throws DataBaseException 
     *     - When Exception occurs 
     */
    public List<Patient> retrievePatients() throws DataBaseException;

    /**
     * <p>
     * Retrieves an patient by their ID.
     * </p>
     *
     * @param id 
     *     - patient ID
     * @return Patient 
     *     - patient details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public Patient retrievePatientById(int id) throws DataBaseException;

    /**
     * <p>
     * Updates an patient based on their ID.
     * </p>
     *
     * @param Patient 
     *     - Updated Patient details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updatePatient(Patient patient) throws DataBaseException;

    /**
     * <p>
     * Deletes an Patient based on their ID.
     * </p>
     *
     * @param Patient 
     *     - Updated Patient details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deletePatient(Patient patient) throws DataBaseException;
}