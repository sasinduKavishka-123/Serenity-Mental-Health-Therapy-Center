package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.dto.PatientDTO;

import java.util.List;

public interface PatientBO extends SuperBO {

    boolean savePatient(PatientDTO p);
    List<PatientDTO> getAllPatients();
    int checkDuplicateData(String name, String contact);
    String getNextID();
}
