package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.dto.TherapistDTO;

import java.util.List;

public interface TherapistBo extends SuperBO{
    boolean saveTherapist(TherapistDTO t);
    List<TherapistDTO> getAllTherapists();
    int checkDuplicateData(int id, String name, String contact, String email, String type);
}
