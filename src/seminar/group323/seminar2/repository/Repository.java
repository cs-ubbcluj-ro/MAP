package seminar.group323.seminar2.repository;

import lecture.livecoding.util.MyLinkedList;
import seminar.group323.seminar2.domain.Entity;

import java.util.Collection;
import java.util.Iterator;

public class Repository<T extends Entity> implements IRepository<T> {

    MyLinkedList<T> data = new MyLinkedList<>();

    @Override
    public void add(T item) {
        data.add(item);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Collection<T> getAll() {
//        return data;
        return null;
    }

    @Override
    public T findById(int id) throws RepositoryException {
        for (T elem : data) {
            if (id == elem.getId()) {
                return elem;
            }
        }
        throw new RepositoryException("Noooooo!");
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Iteratorul sa lucreze pe o copie a listei originale
        // de ce?? - in cazul in care se poate modifica lista din repo
        return data.iterator();
    }
}
