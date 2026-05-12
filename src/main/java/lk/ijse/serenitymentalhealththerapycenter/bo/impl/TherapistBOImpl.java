package lk.ijse.serenitymentalhealththerapycenter.bo.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.TherapistBo;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.TherapistDao;
import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TherapistBOImpl implements TherapistBo {

    TherapistDao therapistDao = (TherapistDao) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.THERAPIST);

    @Override
    public boolean saveTherapist(TherapistDTO t) {
        Therapist therapist = new Therapist();
        therapist.setName(t.getName());
        therapist.setContact(t.getContact());
        therapist.setEmail(t.getEmail());

        return therapistDao.save(therapist);
    }

    @Override
    public boolean updateTherapist(TherapistDTO t) {
        Therapist therapist = new Therapist();
        therapist.setId(Integer.parseInt(t.getId()));
        therapist.setName(t.getName());
        therapist.setContact(t.getContact());
        therapist.setEmail(t.getEmail());

        return therapistDao.update(therapist);
    }

    @Override
    public boolean deleteTherapist(int id) {
        return therapistDao.delete(id);
    }

    @Override
    public List<TherapistDTO> getAllTherapists() {
        List<Therapist> therapistList = therapistDao.getAll();
        List<TherapistDTO> therapistDTOS = new ArrayList<>();
        for(Therapist t : therapistList){
            TherapistDTO tDto = new TherapistDTO();
            tDto.setId("T_"+t.getId());
            tDto.setName(t.getName());
            tDto.setContact(t.getContact());
            tDto.setEmail(t.getEmail());

            therapistDTOS.add(tDto);
        }
        return therapistDTOS;
    }

    @Override
    public List<TherapistDTO> searchTherapists(String text) {
        List<Therapist> therapists = therapistDao.search(text);
        List<TherapistDTO> therapistDTOS = new ArrayList<>();
        for(Therapist t : therapists){
            TherapistDTO tDto = new TherapistDTO();
            tDto.setId("T_"+t.getId());
            tDto.setName(t.getName());
            tDto.setContact(t.getContact());
            tDto.setEmail(t.getEmail());

            therapistDTOS.add(tDto);
        }
        return  therapistDTOS;
    }

    @Override
    public int checkDuplicateData(int id, String name, String contact, String email, String type) {
        return therapistDao.checkDuplicateData(id, name, contact, email, type);
    }

    @Override
    public String getNextID() {
        return therapistDao.getNextID();
    }

    @Override
    public boolean addProgram(TherapistDTO t, ProgramDTO p) {
        Therapist therapist = new Therapist();
        therapist.setId(Integer.parseInt(t.getId().substring(2)));
        therapist.setName(t.getName());
        therapist.setContact(t.getContact());
        therapist.setEmail(t.getEmail());

        Program program = new Program();
        program.setId(Integer.parseInt(p.getId().substring(3)));
        program.setName(p.getName());
        program.setDuration(p.getDuration());
        program.setFee(p.getFee());

        return therapistDao.addProgram(therapist, program);
    }
}
