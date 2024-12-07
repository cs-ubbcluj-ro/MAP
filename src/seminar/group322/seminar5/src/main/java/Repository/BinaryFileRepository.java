package Repository;

import Domain.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class BinaryFileRepository<T extends Entity> extends Repository<T> {
    String filename;

    public BinaryFileRepository(String filename) throws RepositoryException {
        this.filename = filename;
        loadFromBinaryFile();
    }

    @Override
    public void add(T entity) throws RepositoryException {
        super.add(entity);
        saveToBinaryFile();
    }

    private void saveToBinaryFile() throws RepositoryException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(entities);
            out.close();
        } catch (IOException e) {
            throw new RepositoryException("Eroare la incarcarea fisierului:" + e.getMessage());
        }
    }

    private void loadFromBinaryFile() throws RepositoryException {
        ObjectInputStream in = null;
        List<Entity> list = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            entities = (ArrayList<T>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException("Eroare la citirea din fisier: " + e.getMessage());
        }
    }
}
