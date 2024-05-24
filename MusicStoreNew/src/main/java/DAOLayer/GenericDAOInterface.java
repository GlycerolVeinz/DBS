package DAOLayer;

import jakarta.persistence.EntityManager;

import java.util.List;

public interface GenericDAOInterface<T,ID> {
    T findById(ID id);
    List<T> findAll();
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    String getEntityClassName();
    EntityManager getEntityManager();
}
