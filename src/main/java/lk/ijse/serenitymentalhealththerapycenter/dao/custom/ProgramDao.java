package lk.ijse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.serenitymentalhealththerapycenter.dao.CrudDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;

import java.util.List;

public interface ProgramDao extends CrudDao<Program> {
    String getNextID();
    List<Program> getAllProgramsWithTherapists();
    Program getDataByID(int id);
}
