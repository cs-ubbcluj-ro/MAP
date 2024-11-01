package lecture.livecoding.repository;

import lecture.livecoding.domain.Shape2D;

/**
 * An abstract implementation of a file-backed repository. Notice that while derived from a non-abstract class, adding
 * two abstract methods required marking this class as abstract itself. The main idea here is that we know we want to
 * use files to store the repository data, but we want to have the freedom to implement this in more than one way (e.g.,
 * text- or binary- file).
 * <p>
 * This approach allowed us to override the methods which modify the repository data so that everything is now stored in
 * files without explicitly knowing "what kind of" files. Non-abstract subclasses will implement only the saveFile() and
 * loadFile() abstract methods and the resulting repository will work correctly.
 *
 * @param <T> Type of objects that can be stored in the repository. Notice the upper bound "extends Shape2D", which
 *            says that T must extend Shape2D in order to be eligible for being stored here.
 */
public abstract class AbstractFileRepository<T extends Shape2D> extends MemoryRepository<T> {
    protected String fileName;

    public AbstractFileRepository(String fileName) {
        this.fileName = fileName;
        try {
            loadFile();
        } catch (RepositoryException e) {
            // Since we did not declare any thrown exceptions for the class constructor, we must raise one that is
            // derived from RuntimeException (Java's unchecked exceptions do not have to be explicitly declared to be
            // thrown, nor do they need to be try { } catch(...). )
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(T elem) throws RepositoryException {
        super.add(elem);
        // It is possible that the line above raises an exception (in case of duplicate object id's). In that case, the
        // line below does not execute. This is desired behaviour, since raising an exception means that the repository
        // contents were not updated - so there is nothing new to save to the file.
        saveFile();
    }

    @Override
    public void delete(T elem) throws RepositoryException {
        super.delete(elem);
        // Same situation as for the add() method
        saveFile();
    }

    /**
     * Non-abstract subclasses will have to implement this method. There are many exceptions that can appear when
     * working with files/databases.
     *
     * @throws RepositoryException
     */
    protected abstract void saveFile() throws RepositoryException;

    /**
     * Non-abstract subclasses will have to implement this method. There are many exceptions that can appear when
     * working with files/databases.
     *
     * @throws RepositoryException
     */
    protected abstract void loadFile() throws RepositoryException;

}
