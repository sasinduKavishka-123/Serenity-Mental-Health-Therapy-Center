package lk.ijse.serenitymentalhealththerapycenter.model;

import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PatientModel {

    public boolean savePatient(Patient patient){
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

    public List<Patient> getAllCustomers() throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Patient> patients = session.createQuery("from Patient ", Patient.class).list();

        return patients;
    }

    public int checkDuplicateData(String p_name, String p_contact){
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Patient> patientQuery = session.createQuery("from Patient where name='" + p_name + "'", Patient.class);
        if(patientQuery.stream().findAny().isPresent()){
            return 0;
        }
        patientQuery = session.createQuery("from Patient where contact='" + p_contact + "'", Patient.class);
        if(patientQuery.stream().findAny().isPresent()){
            return 1;
        }
        return -1;
    }

}
