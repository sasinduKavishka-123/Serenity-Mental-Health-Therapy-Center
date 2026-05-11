package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.bo.impl.PatientBOImpl;
import lk.ijse.serenitymentalhealththerapycenter.bo.impl.TherapistBOImpl;

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
        PATIENT,
        THERAPIST
    }

    public SuperBO getBO(BOTypes boType){
        switch (boType){
            case PATIENT    : return new PatientBOImpl();
            case THERAPIST  : return new TherapistBOImpl();
            default         : return null;
        }
    }
}
