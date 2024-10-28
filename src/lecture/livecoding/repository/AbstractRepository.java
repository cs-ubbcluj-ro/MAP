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

     /**
     * Add a new element to the repository
     *
     * @param elem The element to be added
     * @throws RepositoryException In case an element having the same ID already exists in the repository
     */
    public abstract void add(T elem) throws RepositoryException;

    /**
     * Remove the given element from the repository
     *
     * @param elem The element to be removed
     * @throws RepositoryException In case the element does not exist
     */
    public abstract void delete(T elem) throws RepositoryException;

    /**
     * Find the object having the given ID in the repository
     *
     * @param id The id value to search for
     * @return The element, or null in case it was not found
     */
    public abstract T find(int id);

    /**
     * Return the size of the repository
     *
     * @return The number of objects in the repository
     */
    public int size() {
        return this.data.size();
    }

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
