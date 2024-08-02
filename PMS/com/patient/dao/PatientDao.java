package com.patient.dao;

import java.util.List;

import com.exception.DataBaseException;
import com.model.Patient;

/**
 * <p>
 * Manage the patient records: add, retrieve, update, and delete.
 * </p>
 */
public interface PatientDao {
     
    /**
     * <p>
     * Add patient if the patient is not present, otherwise update's the patient.
     * </p>
     *
     * @param patient
     *     - patient details 
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public void saveOrUpdatePatient(Patient patient) throws DataBaseException;

    /**
     * <p>
     * Retrieves all employees.
     * </p>
     * 
     * @return List<Patient>
     *     - List of patient details
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public List<Patient> retrievePatients() throws DataBaseException;
}