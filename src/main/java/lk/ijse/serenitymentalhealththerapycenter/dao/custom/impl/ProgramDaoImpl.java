package lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.serenitymentalhealththerapycenter.dao.custom.ProgramDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;

import java.util.List;


public class ProgramDaoImpl implements ProgramDao {
    @Override
    public boolean save(Program entity) {
        return false;
    }

    @Override
    public boolean update(Program entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Program> search(String text) {
        return List.of();
    }

    @Override
    public List<Program> getAll() {
        return List.of();
    }
}
