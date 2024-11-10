package seminar.group323.seminar2.repository;

import seminar.group323.seminar2.domain.Entity;

import java.io.*;
import java.util.List;

public class BinaryFileRepository<T extends Entity> extends Repository<T> {
    private String fileName = "data.bin";

    public BinaryFileRepository(String fileName) {
        this.fileName = fileName;
        try {
            loadData();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadData() throws RepositoryException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            this.data = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                throw new RepositoryException(e.getMessage());
            }
        }
    }

    private void saveData() throws RepositoryException {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // NOTE finally se executa indiferent de exceptiile aruncate
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RepositoryException("");
                }
            }
        }
    }

    @Override
    public void add(T item) throws RepositoryException {
        super.add(item);
        // daca executia ajunge aici, inseamna ca
        // super.add(item) nu a aruncat exceptie, si putem salva fisierul
        saveData();
    }
}
