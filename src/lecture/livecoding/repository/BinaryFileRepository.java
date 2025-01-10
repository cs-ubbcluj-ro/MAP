package lecture.livecoding.repository;

import lecture.livecoding.domain.Shape2D;

import java.io.*;
import java.util.ArrayList;

/**
 * Implements a file-backed repository using Java's object serialization mechanism
 * (https://www.baeldung.com/java-serialization). Notice that we only have to implement the methods marked as abstract
 * in this class's parent. The rest of the code is inherited from base classes and should work accordingly.
 *
 * @param <T>
 */
public class BinaryFileRepository<T extends Shape2D> extends AbstractFileRepository<T> {

    public BinaryFileRepository() {
        this("shapes.bin");
    }

    public BinaryFileRepository(String fileName) {
        super(fileName);
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
    protected void saveFile() throws RepositoryException {

        // Resursele initializate in try() trebuie sa implementeze AutoCloseable
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage(), e);
        }


//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(new FileOutputStream(fileName));
//            oos.writeObject(data);
//            // TODO If there is an exception raised here, who is responsible for closing the stream?
//
//        } catch (IOException exc) {
//            throw new RepositoryException("Eroare la salvarea fisierului", exc);
//        } finally {
//            try {
//                oos.close();
//            } catch (IOException e) {
//                throw new RepositoryException(e.getMessage());
//            }
//        }
    }

    @Override
    protected void loadFile() throws RepositoryException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            this.data = (ArrayList<T>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException exc) {
            // NOTE Not a good idea as console output is easily ignored, but we use it for showing when the repository
            // is initialized
            System.out.println("Fisierul a fost initializat!");
        } catch (IOException | ClassNotFoundException exc) {
            // Repository methods should generally raise RepositoryException objects, or objects derived from the
            // RepositoryException class. That's why we catch java.io exceptions here, we wrap them inside a
            // RepositoryException object and throw them like that. This is called exception chaining in Java
            // (https://www.geeksforgeeks.org/chained-exceptions-java/) and allows us to observe the requirements of
            // this method without losing important information for later debugging.
            throw new RepositoryException("Eroare la incarcarea fisierului", exc);
        }
    }
}
