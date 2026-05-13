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
        Patient patient = new Patient();
        patient.setName(p.getName());
        patient.setGender(p.getGender());
        patient.setContact(p.getContact());
        patient.setAddress(p.getAddress());

        return patientDao.save(patient);
    }

    @Override
    public boolean updatePatient(PatientDTO p) {
        Patient patient = new Patient();
        patient.setId(Integer.parseInt(p.getId()));
        patient.setName(p.getName());
        patient.setGender(p.getGender());
        patient.setContact(p.getContact());
        patient.setAddress(p.getAddress());

        return patientDao.update(patient);
    }

    @Override
    public boolean deletePatient(int id) {
        return patientDao.delete(id);
    }

    @Override
    public List<PatientDTO> searchPatient(String text) {
        List<Patient> pList = patientDao.search(text);
        List<PatientDTO> patientDTOS = new ArrayList<>();

        for(Patient p : pList){
            PatientDTO pDTO = new PatientDTO();
            pDTO.setId("P_" + p.getId());
            pDTO.setName(p.getName());
            pDTO.setGender(p.getGender());
            pDTO.setContact(p.getContact());
            pDTO.setAddress(p.getAddress());

            patientDTOS.add(pDTO);
        }
        return patientDTOS;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> pList = patientDao.getAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for(Patient p : pList){
            PatientDTO pDTO = new PatientDTO();
            pDTO.setId("P_" + p.getId());
            pDTO.setName(p.getName());
            pDTO.setGender(p.getGender());
            pDTO.setContact(p.getContact());
            pDTO.setAddress(p.getAddress());

            patientDTOS.add(pDTO);
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
