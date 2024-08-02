package com.patient.service;

import java.util.ArrayList;
import java.util.List;

import com.exception.DataBaseException;
import com.patient.dao.PatientDao;
import com.patient.dao.PatientDaoImpl;
import com.patient.service.PatientService;
import com.model.Patient;

/**
 * <p>
 * Implementation of PatientService interface.
 * </p>
 */
public class PatientServiceImpl implements PatientService {
    
    private PatientDao patientDao = new PatientDaoImpl();
    
    @Override
    public void createPatient(Patient patient) throws DataBaseException {
        projectDao.saveOrUpdateDepartment(patient);
    }

    @Override
    public List<Patient> retrievePatients() throws DataBaseException {
        List<Patient> allPatients = projectDao.retrievePatients();
        if (allPatients.isEmpty()) {
            return null;
        }
        return allPatients;
    }

    @Override
    public Patient retrievePatientById(int id) throws DataBaseException {
        Patient getPatient = null;
        List<Patient> patients = patientDao.retrievePatients();
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                getPatient = patient;
            } 
        }
        return getPatient;
    }

    @Override
    public void updatePatient(Patient patient) throws DataBaseException {
        projectDao.saveOrUpdateProject(patient);
    }

    @Override
    public void deletePatient(Patient patient) throws DataBaseException {
        patientDao.saveOrUpdatePatient(patient);
    }
}
