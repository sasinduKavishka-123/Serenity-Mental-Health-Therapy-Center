package lk.ijse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.serenitymentalhealththerapycenter.dao.CrudDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;

public interface TherapistDao extends CrudDao<Therapist> {
    int checkDuplicateData(int id, String name, String contact, String email, String type);
    String getNextID();
    boolean addProgram(Therapist t, Program p);
    boolean removeProgram(int t_id, int p_id);
}
