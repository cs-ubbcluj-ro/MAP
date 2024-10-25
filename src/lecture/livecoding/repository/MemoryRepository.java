package lecture.livecoding.repository;

import lecture.livecoding.domain.Shape;

public class MemoryRepository<T extends Shape> extends AbstractRepository<T> {
    @Override
    public void add(T elem) throws RepositoryException {
        if (find(elem.getId()) != null) {
            throw new DuplicateIDException("Object already in repo - " + elem.getId());
        }
        data.add(elem);
    }

    @Override
    public void delete(T elem) throws RepositoryException {
        if (!data.remove(elem)) {
            throw new RepositoryException("Element was not found - " + elem.getId());
        }
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
