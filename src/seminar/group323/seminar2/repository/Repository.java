package seminar.group323.seminar2.repository;

import seminar.group323.seminar2.domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Repository<T extends Entity> implements IRepository<T> {

    List<T> data = new ArrayList<>();

    @Override
    public void add(T item) throws RepositoryException {
        try {
            findById(item.getId());
        } catch (RepositoryException e) {
            data.add(item);
            return;
        }
        throw new RepositoryException("Duplicate id=" + item.getId());
    }

    @Override
    public void remove(int id) {
        // TODO de implementat
    }

    @Override
    public Collection<T> getAll() {
        return data;
    }

    @Override
    public T findById(int id) throws RepositoryException {
        for (T elem : data) {
            if (id == elem.getId()) {
                return elem;
            }
        }
        // NOTE Aruncarea exceptiilor e scumpa computational :)
        throw new RepositoryException("Element with id not found=" + id);
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Iteratorul sa lucreze pe o copie a listei originale
        // de ce?? - in cazul in care se poate modifica lista din repo
        return data.iterator();
    }
}
