package ServiceLayer;

import java.util.Optional;

public interface GenericServiceInterface<T,ID> {
    Optional<T> findById(ID id);
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}
