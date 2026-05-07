package lk.ijse.serenitymentalhealththerapycenter.model;

import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class PatientModel {

//    public boolean savePatient(Patient patient){
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            session.save(patient);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            transaction.rollback();
//            e.printStackTrace();
//            return false;
//        }finally {
//            session.close();
//        }
//    }

//    public List<Patient> getAllCustomers() throws Exception{
//        Session session = FactoryConfiguration.getInstance().getSession();
//        List<Patient> patients = session.createQuery("from Patient ", Patient.class).list();
//        session.close();
//        return patients;
//    }

//    public int checkDuplicateData(String p_name, String p_contact){
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Query<Patient> patientQuery = session.createQuery("from Patient where name='" + p_name + "'", Patient.class);
//        int num = -1;
//        if(patientQuery.stream().findAny().isPresent()){
//            num = 0;
//        }
//        patientQuery = session.createQuery("from Patient where contact='" + p_contact + "'", Patient.class);
//        if(patientQuery.stream().findAny().isPresent()){
//            num = 1;
//        }
//        session.close();
//        return num;
//    }

//    public String getNextID(){
//        Session session = FactoryConfiguration.getInstance().getSession();
//        List<Patient> patientQuery = session.createQuery("FROM Patient ORDER BY id DESC", Patient.class).list();
//        int lastID =  (patientQuery.getFirst().getId() + 1);
//        return ("P_" + lastID);
//    }

}
