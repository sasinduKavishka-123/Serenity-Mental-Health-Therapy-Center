package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.bo.impl.PatientBOImpl;
import lk.ijse.serenitymentalhealththerapycenter.bo.impl.ProgramBOImpl;
import lk.ijse.serenitymentalhealththerapycenter.bo.impl.SessionBOImpl;
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
        THERAPIST,
        PROGRAM,
        SESSION
    }

    public SuperBO getBO(BOTypes boType){
        switch (boType){
            case PATIENT    : return new PatientBOImpl();
            case THERAPIST  : return new TherapistBOImpl();
            case PROGRAM    : return new ProgramBOImpl();
            case SESSION    : return new SessionBOImpl();
            default         : return null;
        }
    }
}
