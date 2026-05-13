package lk.ijse.serenitymentalhealththerapycenter.bo.impl;

import lk.ijse.serenitymentalhealththerapycenter.bo.SessionBO;
import lk.ijse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.SessionDao;
import lk.ijse.serenitymentalhealththerapycenter.dto.SessionDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Payment;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lk.ijse.serenitymentalhealththerapycenter.entity.Sessions;
import lk.ijse.serenitymentalhealththerapycenter.entity.Therapist;

public class SessionBOImpl implements SessionBO {

    SessionDao sessionDao = (SessionDao) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.SESSION);

    @Override
    public boolean saveSession(SessionDTO s) {
        Sessions session = new Sessions();
        Program program = new Program();
        Therapist therapist = new Therapist();
        therapist.setId(Integer.parseInt(s.getTherapist().getId().substring(2)));
        program.setId(Integer.parseInt(s.getProgram().getId().substring(3)));

        session.setDay(s.getDay());
        session.setTime(s.getTime());
        session.setProgram(program);
        session.setTherapist(therapist);

        return sessionDao.save(session);
    }

    @Override
    public String getNextID() {
        return sessionDao.getNextID();
    }
}
