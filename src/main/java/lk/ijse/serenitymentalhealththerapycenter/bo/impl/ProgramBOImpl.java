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
        Program program = new Program();
        program.setName(p.getName());
        program.setDuration(p.getDuration());
        program.setFee(p.getFee());
        return programDao.save(program);
    }

    @Override
    public boolean updateProgram(ProgramDTO p) {
        Program program = new Program();
        program.setId(Integer.parseInt(p.getId()));
        program.setName(p.getName());
        program.setDuration(p.getDuration());
        program.setFee(p.getFee());
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
            ProgramDTO pDto = new ProgramDTO();
            pDto.setId("PR_"+p.getId());
            pDto.setName(p.getName());
            pDto.setDuration(p.getDuration());
            pDto.setFee(p.getFee());

            programDTOS.add(pDto);
        }
        return programDTOS;
    }

    @Override
    public List<ProgramDTO> getAllPrograms() {
        List<Program> programs = programDao.getAll();
        List<ProgramDTO> programDTOS = new ArrayList<>();
        for(Program p : programs){
            ProgramDTO pDto = new ProgramDTO();
            pDto.setId("PR_"+p.getId());
            pDto.setName(p.getName());
            pDto.setDuration(p.getDuration());
            pDto.setFee(p.getFee());

            programDTOS.add(pDto);
        }
        return programDTOS;
    }

    @Override
    public String getNextID() {
        return programDao.getNextID();
    }

}
