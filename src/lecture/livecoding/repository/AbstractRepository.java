package lecture.livecoding.repository;

import lecture.livecoding.domain.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractRepository<T extends Shape> implements Iterable<T> {
    // T reprezinta tipul de referinta stocat de Repository
    // T poate fi orice tip de referinta (String, Plane, Integer, Double)

    protected List<T> data = new ArrayList<>();

    // exceptie daca obiectul deja exista in repo
    public abstract void add(T elem) throws RepositoryException;

    // exceptie daca obiectul nu exista
    public abstract void delete(T elem) throws RepositoryException;

    // exceptie daca id-ul nu exista
    public abstract T find(int id);

    public Collection<T> getAll() {
        // ArrayList e clasa derivata din Collection
        // aici avem un "covariant return"
        return new ArrayList<>(data);
    }

    @Override
    public Iterator<T> iterator() {
        // Iteratorul peste repository este de fapt iteratorul listei
        return data.iterator();
    }
}
