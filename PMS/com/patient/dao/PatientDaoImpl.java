package com.patient.dao;

import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.exception.DataBaseException;
import com.helper.HibernateManage;
import com.model.Patient; 

public class PatientDaoImpl {

    @Override
    public void saveOrUpdatePatient(Patient patient) throws DataBaseException {  
        Transaction transaction = null;
        try (Session session = HibernateManage.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(patient);
            transaction.commit();
        } catch (Exception e) {
            HibernateManage.rollBackTransaction(transaction);
            throw new DataBaseException("Issue while creating the patient");
        }
    }

    @Override
    public List<Patient> retrievePatients() throws DataBaseException {  
        try (Session session = HibernateManage.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Patient"); 
            return query.list();
        } catch (Exception e) {
            throw new DataBaseException("Issue while retrieve the patient");
        }
    }
}
