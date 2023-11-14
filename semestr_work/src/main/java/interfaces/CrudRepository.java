package interfaces;

public interface CrudRepository<T> {
    void save (T entity);
}
