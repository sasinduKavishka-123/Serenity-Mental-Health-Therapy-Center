package lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.SessionDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.serenitymentalhealththerapycenter.entity.Sessions;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SessionDaoImpl implements SessionDao {
    @Override
    public boolean save(Sessions s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(s);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            if(transaction != null) {transaction.rollback();}
            return false;
        }
        finally {
            session.close();
        }

    }

    @Override
    public boolean update(Sessions entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Sessions> search(String text) {
        return List.of();
    }

    @Override
    public List<Sessions> getAll() {
        return List.of();
    }

    @Override
    public String getNextID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Sessions> sessionQuery = session.createQuery("FROM Sessions ORDER BY id DESC", Sessions.class).list();
        int lastID ;
        if(sessionQuery.isEmpty()){
            lastID = 1;
        }
        else{
            lastID = sessionQuery.getFirst().getId() + 1;
        }
        session.close();
        return ("S_" + lastID);
    }

}
