package com.diagnosis.dao;

import java.util.List;

import com.exception.DataBaseException;
import com.model.Diagnosis;

/**
 * <p>
 * Manage the diagnosis records: add, retrieve, update, and delete.
 * </p>
 */
public interface DiagnosisDao {
     
    /**
     * <p>
     * Add diagnosis if the diagnosis is not present, otherwise update's the diagnosis.
     * </p>
     *
     * @param Diagnosis
     *     - diagnosis details 
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public void saveOrUpdateDiagnosis(Diagnosis diagnosis) throws DataBaseException;

    /**
     * <p>
     * Retrieves all Diagnosis.
     * </p>
     * 
     * @return List<Diagnosis>
     *     - List of diagnosis details
     * @throws DataBaseException 
     *     - when Exception occurs DataBaseException
     */
    public List<Diagnosis> retrieveDiagnosis() throws DataBaseException;
}