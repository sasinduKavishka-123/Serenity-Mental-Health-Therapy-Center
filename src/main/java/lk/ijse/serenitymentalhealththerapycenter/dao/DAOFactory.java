package lk.ijse.serenitymentalhealththerapycenter.dao;

import lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl.PatientDaoImpl;

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
        PATIENT
    }

    public SuperDao getDao(DAOTypes daoTypes){
        switch (daoTypes){
            case PATIENT: return new PatientDaoImpl();
            default: return null;
        }
    }

}
