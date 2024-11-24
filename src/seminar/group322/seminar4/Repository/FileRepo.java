package seminar.group322.seminar4.Repository;

import seminar.group322.seminar4.Domain.Entity;
import seminar.group322.seminar4.Domain.IEntityFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRepo<T extends Entity> extends Repository<T> {
    String fileName;
    IEntityFactory<T> factory;

    public FileRepo(String fileName, IEntityFactory<T> factory) throws RepositoryException {
        this.fileName = fileName;
        this.factory = factory;
        this.ReadFromFile();
    }

    ///Man Daniel 322
    public void ReadFromFile() throws RepositoryException {
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RepositoryException("Eroare la incarcare din fisier: " + e.getMessage());
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            T entityToAdd = factory.createEntity(line);
            super.add(entityToAdd);
        }
    }
}
