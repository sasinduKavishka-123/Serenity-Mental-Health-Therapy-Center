package lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.TherapistDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TherapistDaoImpl implements TherapistDao {
    @Override
    public boolean save(Therapist therapist) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(therapist);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Therapist entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Therapist> search(String text) {
        return List.of();
    }

    @Override
    public List<Therapist> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Therapist> therapists = session.createQuery("from Therapist ", Therapist.class).list();
        session.close();
        return therapists;
    }

    @Override
    public int checkDuplicateData(int t_id, String t_name, String t_contact, String t_email, String type) {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Therapist> therapistQuery = session.createQuery("from Therapist where name='" + t_name + "'", Therapist.class).list();

        if(therapistQuery.stream().findAny().isPresent()){
            if((therapistQuery.size() == 1) && type.equals("u")){
                if(!(therapistQuery.getFirst().getId() == t_id)){
                    session.close();
                    return 0;
                }
            }else{
                session.close();
                return 0;
            }
        }
        therapistQuery = session.createQuery("from Therapist where contact='" + t_contact + "'", Therapist.class).list();
        if(therapistQuery.stream().findAny().isPresent()){
            if((therapistQuery.size() == 1) && type.equals("u")){
                if(!(therapistQuery.getFirst().getId() == t_id)){
                    session.close();
                    return 1;
                }
            }else{
                session.close();
                return 1;
            }
        }
        therapistQuery = session.createQuery("from Therapist where email='" + t_email + "'", Therapist.class).list();
        if(therapistQuery.stream().findAny().isPresent()){
            if((therapistQuery.size() == 1) && type.equals("u")){
                if(!(therapistQuery.getFirst().getId() == t_id)){
                    session.close();
                    return 2;
                }
            }else{
                session.close();
                return 2;
            }
        }
        session.close();
        return -1;
    }
}
