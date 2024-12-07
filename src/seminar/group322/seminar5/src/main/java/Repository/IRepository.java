package Repository;

import Domain.Entity;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    void add(T entity) throws RepositoryException;
    void remove(int id) throws RepositoryException;
    T find(int id);
    Collection<T> getAll();
}