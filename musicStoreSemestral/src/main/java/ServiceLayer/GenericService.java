package ServiceLayer;

import DAOLayer.GenericDAO;

import java.util.Optional;

public class GenericService<T,ID> implements GenericServiceInterface<T,ID> {
    protected final GenericDAO<T,ID> dao;

    public GenericService(GenericDAO<T, ID> dao) {
        this.dao = dao;
    }

    @Override
    public Optional<T> findById(ID id) {
        Optional<T> entity = Optional.ofNullable(dao.findById(id));
        if (entity.isEmpty()){
            System.err.println("Entity not found with id: " + id
                    + "\nin table: " + dao.getEntityClass().getSimpleName()
                    + "\n(try checking all of the ids in the table)");
        }

        return entity;
    }

    @Override
    public boolean save(T entity) {
        boolean isActionSuccessful = dao.save(entity);
        if (!isActionSuccessful){
            System.err.println("Error saving entity: " + entity.toString());
        }
        return isActionSuccessful;
    }

    @Override
    public boolean update(T entity) {
        boolean isActionSuccessful = dao.update(entity);
        if (!isActionSuccessful){
            System.err.println("Error updating entity: " + entity.toString());
        }
        return isActionSuccessful;
    }

    @Override
    public boolean delete(T entity) {
        boolean isActionSuccessful = dao.delete(entity);
        if (!isActionSuccessful){
            System.err.println("Error deleting entity: " + entity.toString());
        }
        return isActionSuccessful;
    }

    public GenericDAO<T,ID> getDAO() {
        return dao;
    }
}
