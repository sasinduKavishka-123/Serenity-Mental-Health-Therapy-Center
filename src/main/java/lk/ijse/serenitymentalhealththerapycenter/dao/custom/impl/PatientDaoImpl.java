package lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.PatientDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PatientDaoImpl implements PatientDao {

    @Override
    public boolean save(Patient patient) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(patient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Patient> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Patient> patients = session.createQuery("from Patient ", Patient.class).list();
        session.close();
        return patients;
    }

    @Override
    public int checkDuplicateData(String p_name, String p_contact) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Patient> patientQuery = session.createQuery("from Patient where name='" + p_name + "'", Patient.class);

        if(patientQuery.stream().findAny().isPresent()){
            return 0;
        }
        patientQuery = session.createQuery("from Patient where contact='" + p_contact + "'", Patient.class);
        if(patientQuery.stream().findAny().isPresent()){
            return 1;
        }
        session.close();
        return -1;
    }

    @Override
    public String getNextID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Patient> patientQuery = session.createQuery("FROM Patient ORDER BY id DESC", Patient.class).list();
        int lastID =  (patientQuery.getFirst().getId() + 1);
        return ("P_" + lastID);
    }
}
