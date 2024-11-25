package seminar.group323.seminar2.repository;

import org.sqlite.SQLiteDataSource;
import seminar.group323.seminar2.domain.Artist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class SQLArtistRepository extends Repository<Artist> implements AutoCloseable {

    // TODO Locatia bazei de date se poate citi din fisierul de setari

    // NOTE Clasa aceasta nu mai foloseste lista de artisti, ci ii incarca direct
    // din baza de date
    private String JDBC_URL = "jdbc:sqlite:";


    // src/seminar/group323/seminar2/artists.db
    private Connection conn = null;

    public SQLArtistRepository(String databaseFile) {
        JDBC_URL += databaseFile;
        openConnection();
        initDatabase();
    }

    private void initDatabase() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS artists(id int PRIMARY KEY, name varchar(50), genre varchar(50));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    @Override
    public void add(Artist artist) throws RepositoryException {
        super.add(artist);

        // Daca se executa codul de mai jos, inseamna ca nu a avut loc nici o exceptie
        // in super.add(artist);
        try (var statement = conn.prepareStatement("INSERT INTO artists VALUES (?, ?, ?)")) {
            statement.setInt(1, artist.getId());
            statement.setString(2, artist.getName());
            statement.setString(3, artist.getGenre());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Eroare la salvarea artistului: " + e.getMessage());
        }
    }

    @Override
    public Artist findById(int id) throws RepositoryException {
        try (var statement = conn.prepareStatement("SELECT * FROM artists WHERE id = (?)")) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var artist_id = resultSet.getInt(1);
                var artist_name = resultSet.getString(2);
                var artist_genre = resultSet.getString(3);
                return new Artist(artist_id, artist_name, artist_genre);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Eroare la stergerea artistului: " + e.getMessage());
        }
        throw new RepositoryException("Element with id not found=" + id);
    }

    @Override
    public void remove(int id) throws RepositoryException {
        // TODO de implementat in clasa de baza
        super.remove(id);

        try (var statement = conn.prepareStatement("DELETE FROM artists WHERE id = (?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // NOTE
//            e.printStackTrace();
            throw new RepositoryException("Eroare la stergerea artistului: " + e.getMessage());
        }
    }


    @Override
    public Collection<Artist> getAll() {
        var artistList = new ArrayList<Artist>();
        try (var select = conn.prepareStatement("SELECT * FROM artists")) {

            ResultSet resultSet = select.executeQuery();
            while (resultSet.next() == true) {
                var artist_id = resultSet.getInt(1);
                var artist_name = resultSet.getString(2);
                var artist_genre = resultSet.getString(3);
                artistList.add(new Artist(artist_id, artist_name, artist_genre));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return artistList;
    }

    private void openConnection() {
        try {
            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            // Exceptii de tipul RuntimeException se pot arunca fara a fi nevoie de
            // try .. catch (unchacked exceptions)
            throw new RuntimeException(e);
        }
    }


    @Override
    public void close() throws Exception {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            // TODO Probabil de scris in loguri
            e.printStackTrace();
        }
    }
}
