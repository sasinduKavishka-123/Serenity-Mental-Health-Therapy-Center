package lk.ijse.serenitymentalhealththerapycenter.bo.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.ProgramBO;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.ProgramDao;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDao programDao = (ProgramDao) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PROGRAM);

}
