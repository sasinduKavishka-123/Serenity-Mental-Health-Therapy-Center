package lk.ijse.serenitymentalhealththerapycenter.bo.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.ProgramBO;
import lk.ijse.serenitymentalhealththerapycenter.bo.TherapistBo;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.ProgramDao;
import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramTherapistDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;

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

    @Override
    public List<ProgramTherapistDTO> getAllProgramsWithTherapists() {
        List<Program> programs = programDao.getAllProgramsWithTherapists();
        List<ProgramTherapistDTO> ptList = new ArrayList<>();

        for(Program p : programs){
            for(Therapist t : p.getTherapists()){
                ProgramTherapistDTO pt = new ProgramTherapistDTO();
                pt.setPrID("PR_"+p.getId());
                pt.setPrName(p.getName());

                pt.setTID("T_"+t.getId());
                pt.setTName(t.getName());

                ptList.add(pt);
            }
        }
        return ptList;
    }

    @Override
    public ProgramDTO getDataById(String id) {
        Program p = programDao.getDataByID(Integer.parseInt(id.substring(3)));
        ProgramDTO programDTO = new ProgramDTO();
        programDTO.setId("PR_"+ p.getId());
        programDTO.setName(p.getName());
        programDTO.setDuration(p.getDuration());
        programDTO.setFee(p.getFee());

        return programDTO;
    }

}
