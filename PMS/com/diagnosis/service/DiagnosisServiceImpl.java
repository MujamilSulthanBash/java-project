package com.diagnosis.service;

import java.util.ArrayList;
import java.util.List;

import com.exception.DataBaseException;
import com.diagnosis.dao.DiagnosisDao;
import com.diagnosis.dao.DiagnosisDaoImpl;
import com.diagnosis.service.DiagnosisService;
import com.model.Diagnosis;

/**
 * <p>
 * Implementation of DiagnosisService interface.
 * </p>
 */
public class DiagnosisServiceImpl implements DiagnosisService {
    
    private DiagnosisDao diagnosisDao = new DiagnosisDaoImpl();
    
    @Override
    public void createDiagnosis(Diagnosis diagnosis) throws DataBaseException {
        DiagnosisDao.saveOrUpdateDiagnosis(diagnosis);
    }

    @Override
    public List<Diagnosis> retrieveDiagnosis() throws DataBaseException {
        List<Diagnosis> allDiagnosis = diagnosisDao.retrieveDiagnosis();
        if (allDiagnosis.isEmpty()) {
            return null;
        }
        return allDiagnosis;
    }

    @Override
    public Diagnosis retrieveDiagnosisById(int id) throws DataBaseException {
        Diagnosis getDiagnosis = null;
        List<Diagnosis> diagnosis = diagnosisDao.retrieveDiagnosis();
        for (Diagnosis diagnosi : iagnosis) {
            if (diagnosi.getId() == id) {
                getDiagnosis = diagnosi;
            } 
        }
        return getDiagnosis;
    }

    @Override
    public void updateDiagnosis(Diagnosis diagnosis) throws DataBaseException {
        diagnosisDao.saveOrUpdateDiagnosis(diagnosis);
    }

    @Override
    public void deleteDiagnosis(Diagnosis diagnosis) throws DataBaseException {
        diagnosisDao.saveOrUpdateDiagnosis(diagnosis);
    }
}
