package repositories;

public interface CrudRepository<T> {
    void save (T entity);
}
