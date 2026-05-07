package lk.ijse.serenitymentalhealththerapycenter.bo.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.PatientBO;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.PatientDao;
import lk.ijse.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {

    private final PatientDao patientDao = (PatientDao) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PATIENT);

    @Override
    public boolean savePatient(PatientDTO p){
        Patient patient = new Patient(0, p.getName(), p.getGender(), p.getContact(), p.getAddress(), p.getRegisteredDay());
        return patientDao.save(patient);
    }

    @Override
    public boolean updatePatient(PatientDTO p) {
        Patient patient = new Patient(Integer.parseInt(p.getId()), p.getName(), p.getGender(), p.getContact(), p.getAddress(), p.getRegisteredDay());
        return patientDao.update(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> pList = patientDao.getAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for(Patient p : pList){
            patientDTOS.add(new PatientDTO("P_" + p.getId(), p.getName(), p.getGender(), p.getContact(), p.getAddress(), p.getRegisteredDay()));
        }
        return patientDTOS;
    }

    @Override
    public int checkDuplicateData(int id, String name, String contact, String type) {
        return patientDao.checkDuplicateData(id, name, contact, type);
    }

    @Override
    public String getNextID() {
        return patientDao.getNextID();
    }


}
