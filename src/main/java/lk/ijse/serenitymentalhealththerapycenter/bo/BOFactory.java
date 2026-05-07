package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.bo.impl.PatientBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        PATIENT
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case PATIENT: return new PatientBOImpl();
            default: return null;
        }
    }
}
