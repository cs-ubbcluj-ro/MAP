package seminar.group321.seminar3.repository;

import seminar.group321.seminar3.domain.Entity;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    void add(T entity) throws DuplicateEntityException;
    void remove(int id);
    T find(int id);
    Collection<T> getAll();
}