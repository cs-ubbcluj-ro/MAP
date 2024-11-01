package seminar.group321.seminar3.repository;

import seminar.group321.seminar3.domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Repository<T extends Entity> implements IRepository<T> { //Bostan Roger Alex gr 321
    List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (find(entity.getId()) != null) {
            throw new DuplicateEntityException("this id exists");
        }
        entities.add(entity);
    }

    @Override
    public void remove(int id) {
        T entityToFind = find(id);
        // de adaugat exceptie cand nu se gaseste elementul de sters
        entities.remove(entityToFind);
    }

    @Override
    public T find(int id) {
        for (T entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return entities;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }
}
