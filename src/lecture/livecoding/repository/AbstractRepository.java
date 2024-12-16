package lecture.livecoding.repository;

import lecture.livecoding.domain.Shape2D;
import lecture.livecoding.observer.Observable;
import lecture.livecoding.observer.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the base class for a repository. Notice the abstract methods, which require us to mark the class itself as
 * being "abstract". Actual repository implementation will be all derived from this one.
 *
 * @param <T> Type of objects that can be stored in the repository. Notice the upper bound "extends Shape2D", which
 *            says that T must extend Shape2D in order to be eligible for being stored here.
 */
public abstract class AbstractRepository<T extends Shape2D> implements Iterable<T>, Observable {

    // This java.lang.ArrayList is the actual backing store of the repository
    protected List<T> data = new ArrayList<>();

    protected List<Observer> observers = new ArrayList<>();

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
        // ArrayList is derived from java.util.Collection. This method uses a covariant return (since an ArrayList
        // "is a" Collection, you may use an ArrayList in all places where a Collection is expected
        return new ArrayList<>(data);
    }

    @Override
    public Iterator<T> iterator() {
        // The iterator of the repository is a Java iterator. The implementation uses the internal iterator of the
        // repository's backing list.
        return data.iterator();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (var observer : observers) {
            observer.notifyObserver();
        }
    }

}
