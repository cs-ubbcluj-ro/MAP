package seminar.group321.seminar3.repository;

import seminar.group321.seminar3.domain.Entity;
import seminar.group321.seminar3.domain.IEntityFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRepo<T extends Entity> extends Repository<T> {
    private String filepath;
    private IEntityFactory<T> entityFactory;

    public FileRepo(String filepath, IEntityFactory<T> factory) throws DuplicateEntityException, FileNotFoundException {
        this.filepath = filepath;
        this.entityFactory = factory;
        loadEntities();
    }

    private void loadEntities() throws FileNotFoundException, DuplicateEntityException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            T entity = entityFactory.createEntity(line);
            super.add(entity);
        }
        scanner.close();

    }
}
