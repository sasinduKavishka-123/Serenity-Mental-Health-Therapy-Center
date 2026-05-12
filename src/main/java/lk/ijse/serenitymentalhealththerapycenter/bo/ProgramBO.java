package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramTherapistDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.TherapistDTO;

import java.util.List;

public interface ProgramBO extends SuperBO{

    boolean saveProgram(ProgramDTO p);
    boolean updateProgram(ProgramDTO p);
    boolean deleteProgram(int id);
    List<ProgramDTO> searchProgram(String text);
    List<ProgramDTO> getAllPrograms();
    String getNextID();
    List<ProgramTherapistDTO> getAllProgramsWithTherapists();
    ProgramDTO getDataById(String id);
}
