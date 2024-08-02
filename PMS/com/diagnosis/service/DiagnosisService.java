package com.diagnosis.service;

import java.util.List;

import com.exception.DataBaseException;
import com.model.Diagnosis;

/**
 * <p>
 * Managing Diagnosis records, add, retrieve, retrieve by id,update and delete.
 * </p>
 */
public interface DiagnosisService {

    /**
     * <p>
     * Creates a new Diagnosis record.
     * </p>
     *
     * @param Diagnosis 
     *     - Diagnosis details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createDiagnosis(Diagnosis diagnosis) throws DataBaseException;                      

    /**
     * <p>
     * Retrieves Diagnosis.
     * </p>
     *
     * @return List<Diagnosis> 
     *     - List of Diagnosis details
     * @throws DataBaseException 
     *     - When Exception occurs 
     */
    public List<Diagnosis> retrieveDiagnosis() throws DataBaseException;

    /**
     * <p>
     * Retrieves an Diagnosis by their ID.
     * </p>
     *
     * @param id 
     *     - Diagnosis ID
     * @return Diagnosis 
     *     - Diagnosis details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public Diagnosis retrieveDiagnosisById(int id) throws DataBaseException;

    /**
     * <p>
     * Updates an Diagnosis based on their ID.
     * </p>
     *
     * @param Diagnosis 
     *     - Updated Diagnosis details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updateDiagnosis(Diagnosis diagnosis) throws DataBaseException;

    /**
     * <p>
     * Deletes an Diagnosis based on their ID.
     * </p>
     *
     * @param Diagnosis 
     *     - Diagnosis details
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deleteDiagnosis(Diagnosis diagnosis) throws DataBaseException;
}