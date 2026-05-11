package lk.ijse.serenitymentalhealththerapycenter.bo.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.ProgramBO;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.ProgramDao;
import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDao programDao = (ProgramDao) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean saveProgram(ProgramDTO p) {
        Program program = new Program(0, p.getName(), p.getDuration(), p.getFee(), null);
        return programDao.save(program);
    }

    @Override
    public boolean updateProgram(ProgramDTO p) {
        Program program = new Program(Integer.parseInt(p.getId()), p.getName(), p.getDuration(), p.getFee(), null);
        return programDao.update(program);
    }

    @Override
    public boolean deleteProgram(int id) {
        return programDao.delete(id);
    }

    @Override
    public List<ProgramDTO> searchProgram(String text) {
        List<Program> programs = programDao.search(text);
        List<ProgramDTO> programDTOS = new ArrayList<>();
        for(Program p : programs){
            programDTOS.add(new ProgramDTO("PR_"+p.getId(), p.getName(), p.getDuration(), p.getFee()));
        }
        return programDTOS;
    }

    @Override
    public List<ProgramDTO> getAllPrograms() {
        List<Program> programs = programDao.getAll();
        List<ProgramDTO> programDTOS = new ArrayList<>();
        for(Program p : programs){
            programDTOS.add(new ProgramDTO("PR_"+p.getId(), p.getName(), p.getDuration(), p.getFee()));
        }
        return programDTOS;
    }

    @Override
    public String getNextID() {
        return programDao.getNextID();
    }
}
