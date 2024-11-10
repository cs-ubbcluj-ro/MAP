 package lecture.livecoding.repository;

import lecture.livecoding.domain.Shape2D;
import lecture.livecoding.domain.ShapeConverter;

import java.io.*;
import java.util.Objects;

public class TextFileRepository<T extends Shape2D> extends AbstractFileRepository {

    protected ShapeConverter<T> converter;

    public TextFileRepository(String fileName, ShapeConverter<T> converter) {
        super(fileName);
        this.converter = converter;
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for (var shape : this.data) {
                String asString = converter.toString((T) shape);
                bw.write(asString);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RepositoryException("Eroare la scrierea fisierului", e);
        }
    }

    @Override
    protected void loadFile() throws RepositoryException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line = br.readLine();
            while (line != null) {
                data.add(converter.fromString(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RepositoryException("Eroare la citirea fisierului", e);
        }
    }
}
