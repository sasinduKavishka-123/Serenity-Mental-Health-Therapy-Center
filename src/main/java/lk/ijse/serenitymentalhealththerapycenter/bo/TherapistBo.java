package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.TherapistDTO;

import java.util.List;

public interface TherapistBo extends SuperBO{
    boolean saveTherapist(TherapistDTO t);
    boolean updateTherapist(TherapistDTO t);
    boolean deleteTherapist(int id);
    List<TherapistDTO> getAllTherapists();
    List<TherapistDTO> searchTherapists(String text);
    int checkDuplicateData(int id, String name, String contact, String email, String type);
    String getNextID();
    boolean addProgram(TherapistDTO t, ProgramDTO p);
}
