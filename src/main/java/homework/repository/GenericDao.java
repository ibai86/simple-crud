package homework.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID extends Serializable> {

    Optional<T> findById(ID id);

    List<T> findAll();

    Optional<T> save(T entity);

    Optional<T> update(T entity);

    boolean delete(T entity);
}
