package seminar.group323.seminar2.repository;

import seminar.group323.seminar2.domain.Entity;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {


    void add(T item) throws RepositoryException;

    void remove(int id) throws RepositoryException;

    Collection<T> getAll();

    T findById(int id) throws RepositoryException;

}
