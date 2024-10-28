package seminar.group322.seminar2.Repository;


import seminar.group322.seminar2.Domain.Entity;

import java.util.ArrayList;

//Pentru a face Repo Iterable - vezi comentarii folder Seminar 2, grupa 321
public class Repository<T extends Entity> {
    //gestioneaza lista (ArrayList) de entitati
    //se poate adauga entitate
    //      -trebuie verificat sa nu mai existe o entitate cu acelasi ID
    //se poate cauta entitate dupa ID
    //      -returneaza True daca exista entitate cu id dat, false altfel
    //se poate sterge entitate dupa ID

    ArrayList<T> entities = new ArrayList<>();

    public boolean findByID(int id) {
        for (T entity : entities) {
            if (entity.getId() == id)
                return true;
        }
        return false;
    }

    public void addEntity(T entityToAdd) throws Exception {
        if (findByID(entityToAdd.getId())) {
            throw new Exception("Exista deja o entitate cu id dat.");
        }
        entities.add(entityToAdd);
    }

    public T getEntityById(int id) {
        for (T entity : entities) {
            if (entity.getId() == id)
                return entity;
        }
        return null;
    }

    public void removeEntity(int id) throws Exception {
        T entityToDelete = getEntityById(id);
        if (entityToDelete == null) {
            throw new Exception("Nu exista entitate cu ID dat.");
        }
        entities.remove(entityToDelete);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "entities=" + entities +
                '}';
    }
}
