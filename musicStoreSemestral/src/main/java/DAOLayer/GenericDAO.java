package DAOLayer;

import jakarta.persistence.EntityManager;

import java.util.List;

public class GenericDAO<T, ID> implements GenericDAOInterface<T, ID> {
    protected final Class<T> entityClass;
    protected final EntityManager entityManager;

    public GenericDAO(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    public boolean save(T entity) {
        boolean result;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            result = false;
        }

        return result;
    }

    public boolean update(T entity) {
        boolean result;
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            result = false;
        }

        return result;
    }

    public boolean delete(T entity) {
        boolean result;
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            result = false;
        }

        return result;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
