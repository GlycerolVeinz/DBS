package ServiceLayer;

import java.util.List;
import java.util.Optional;

public interface GenericServiceInterface<T,ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}
