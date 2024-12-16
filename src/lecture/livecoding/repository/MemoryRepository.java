package lecture.livecoding.repository;

import lecture.livecoding.domain.Shape2D;

/**
 * A concrete (=non-abstract) implementation of a Repository, which stores all entities in memory. The main limitation
 * is that when the program is existed, all stored information is lost :(
 *
 * @param <T> The type of stored entities - must be a subclass of Shape2D (take a look at the add() method implementation)
 */
public class MemoryRepository<T extends Shape2D> extends AbstractRepository<T> {
    @Override
    public void add(T elem) throws RepositoryException {
        // NOTE The getId() call below is what requires us to specify <T extends Shape2D> in the class definition.
        // Otherwise, how can the compiler be assured that type T will have a getId() method?
        if (find(elem.getId()) != null) {
            // We use our own exception hierarchy in order to hide the complexities of the implementation from the
            // clients of the repository
            throw new DuplicateIDException("Object already in repo - " + elem.getId());
        }
        data.add(elem);
        notifyObservers();
    }

    @Override
    public void delete(T elem) throws RepositoryException {
        if (!data.remove(elem)) {
            throw new RepositoryException("Element was not found - " + elem.getId());
        }
        notifyObservers();
    }

    @Override
    public T find(int id) {
        for (T elem : data) {
            if (id == elem.getId()) {
                return elem;
            }
        }
        return null;
    }
}
