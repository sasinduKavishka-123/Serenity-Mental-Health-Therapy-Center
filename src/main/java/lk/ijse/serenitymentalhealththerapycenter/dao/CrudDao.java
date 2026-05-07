package lk.ijse.serenitymentalhealththerapycenter.dao;

import java.util.List;

public interface CrudDao<T> extends SuperDao{
    boolean save(T entity);
    List<T> getAll();
}
