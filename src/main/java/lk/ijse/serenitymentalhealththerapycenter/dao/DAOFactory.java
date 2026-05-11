package lk.ijse.serenitymentalhealththerapycenter.dao;

import lk.ijse.serenitymentalhealththerapycenter.dao.custom.TherapistDao;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl.PatientDaoImpl;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl.TherapistDaoImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        PATIENT,
        THERAPIST
    }

    public SuperDao getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case PATIENT: return new PatientDaoImpl();
            case THERAPIST: return new TherapistDaoImpl();
            default: return null;
        }
    }

}
