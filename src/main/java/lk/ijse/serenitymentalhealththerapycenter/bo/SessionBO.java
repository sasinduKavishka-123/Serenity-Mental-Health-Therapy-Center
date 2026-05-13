package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.dto.SessionDTO;

public interface SessionBO extends SuperBO{
    boolean saveSession(SessionDTO s);
    String getNextID();
}
