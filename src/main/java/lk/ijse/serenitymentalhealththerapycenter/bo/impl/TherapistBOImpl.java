package lk.ijse.serenitymentalhealththerapycenter.bo.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.TherapistBo;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.TherapistDao;
import lk.ijse.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBo {

    TherapistDao therapistDao = (TherapistDao) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.THERAPIST);

    @Override
    public boolean saveTherapist(TherapistDTO t) {
        Therapist therapist = new Therapist(0, t.getName(), t.getContact(), t.getEmail());
        return therapistDao.save(therapist);
    }

    @Override
    public boolean updateTherapist(TherapistDTO t) {
        Therapist therapist = new Therapist(Integer.parseInt(t.getId()), t.getName(), t.getContact(), t.getEmail());
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
            therapistDTOS.add(new TherapistDTO("T_"+t.getId(), t.getName(), t.getContact(), t.getEmail()));
        }
        return therapistDTOS;
    }

    @Override
    public List<TherapistDTO> searchTherapists(String text) {
        List<Therapist> therapists = therapistDao.search(text);
        List<TherapistDTO> therapistDTOS = new ArrayList<>();
        for(Therapist t : therapists){
            therapistDTOS.add(new TherapistDTO("T_" + t.getId(), t.getName(), t.getContact(), t.getEmail()));
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
}
