package repository;

import domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Repository<T extends Entity> implements IRepository<T>{ //Bostan Roger Alex gr 321
    List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws RepositoryException {
        if(find(entity.getId()) !=null){
            throw new DuplicateEntityException("this id exists");
        }
        entities.add(entity);
    }

    @Override
    public void remove(int id) {
        T entityToFind = find(id);
        // de adaugat exceptia
        entities.remove(entityToFind);
    }

    @Override
    public T find(int id) {
        for(T entity: entities){
            if(entity.getId() == id){
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
        return entities.iterator();
    }
}
