package lk.ijse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.serenitymentalhealththerapycenter.dao.CrudDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.List;

public interface ProgramDao extends CrudDao<Program> {
    String getNextID();
    List<Program> getAllProgramsWithTherapists();
    List<Therapist> getProgramWithTherapists(int id);
    Program getDataByID(int id);
}
