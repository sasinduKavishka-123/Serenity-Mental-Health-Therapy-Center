package lk.ijse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.serenitymentalhealththerapycenter.dao.CrudDao;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;

public interface PatientDao extends CrudDao<Patient> {

    int checkDuplicateData(String name, String contact);
    String getNextID();
}
