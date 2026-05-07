package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.dto.PatientDTO;

import java.util.List;

public interface PatientBO extends SuperBO {

    boolean savePatient(PatientDTO p);
    boolean updatePatient(PatientDTO p);
    boolean deletePatient(int id);
    List<PatientDTO> searchPatient(String text);
    List<PatientDTO> getAllPatients();
    int checkDuplicateData(int id, String name, String contact, String type);
    String getNextID();
}
