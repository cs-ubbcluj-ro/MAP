package seminar.group322.seminar4.Repository;

import seminar.group322.seminar4.Domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Repository<T extends Entity> implements IRepository<T> {

    ArrayList<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws RepositoryException {
        if (entity == null)
            throw new RepositoryException("Entitatea este null, nu este posibila adaugarea.");
        if (find(entity.getId()) != null) {
            throw new DuplicateEntityException("Exista entitate cu id :" + entity.getId() + "!");
        } else
            entities.add(entity);
    }

    @Override
    public void remove(int id) throws NonExistEntity {
        T toDelete = find(id);
        if (toDelete != null)
            entities.remove(toDelete);
        else
            throw new NonExistEntity("Aceasta entitate nu exista!");
    }

    @Override
    public T find(int id) {
        for (T e : entities) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return entities;
    }

    @Override
    public Iterator<T> iterator() {
        return entities.iterator();
    }
}
