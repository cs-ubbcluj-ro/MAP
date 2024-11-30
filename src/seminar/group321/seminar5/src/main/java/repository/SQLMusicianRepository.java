package repository;

import domain.Musician;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Alexandrescu Emilia
public class SQLMusicianRepository extends Repository<Musician> {
    Connection connection;
    //change URL here to relative path or your (absolute) path
    String db_url = "jdbc:sqlite:D:\\Facultate\\Ore 2024-2025\\MAP\\Mine\\Seminar5_321\\src\\main\\java\\musician_db.db";

    public SQLMusicianRepository() {
        openConnection();
        createTable();
        //added from seminar
        loadData();
        
        //initMusicianTable();
    }

    private void loadData() {
        //adding directly to list of entities
        //why not call super.add() pentru fiecare Musician?
        entities.addAll(this.getAll());
    }

    private void initMusicianTable() {
        //Metoda care insereaza mai multi musicians in tabel
        List<Musician> musicianList = new ArrayList<>();
        musicianList.add(new Musician(101, "Andra", 73, "pop"));
        musicianList.add(new Musician(102, "Alin", 29, "pop"));
        musicianList.add(new Musician(103, "Andrei", 19, "folk"));
        musicianList.add(new Musician(104, "Axel", 33, "rock"));
        musicianList.add(new Musician(105, "Carina", 49, "hip-hop"));

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO musicians VALUES (?,?,?,?);")) {
            for (Musician m : musicianList) {
                statement.setInt(1, m.getId());
                statement.setString(2, m.getName());
                statement.setInt(3, m.getAge());
                statement.setString(4, m.getGenre());

            }
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void openConnection() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(db_url);
        try {
            if (connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            System.out.println("eroare la crearea conexiuni" + e);
        }
    }

    private void createTable() {
        String s = "Create Table if not exists musicians( id int, name varchar(100), age int, genre varchar(100), PRIMARY KEY (id) )";
        try {
            //Statement vs. Prepared Statement
            //Statement: --nu poate fi parametrizat
            //   i.e. putem scrie SELECT * FROM musicians WHERE id = " + id+";", dar ce
            //        facem este doar sa construim query-ul prin concatenare de string
            //           --vulnerabil la SQL injection
            Statement statement = connection.createStatement();

            //.execute() returns: true if the first result is a ResultSet object
            //                    false if it is an update count or there are no results
            boolean execution_result = statement.execute(s);
            System.out.println("Execution result from createTable()"+execution_result);
        } catch (SQLException e) {
            System.out.println("eroare la crearea tabelei Musicians" + e);
        }
    }

    @Override
    public void add(Musician m) throws RepositoryException {
        super.add(m);
        String s = "INSERT INTO musicians VALUES (?,?,?,?);";
        try {
            //Prepared Statement
            //permite parametrizarea
            //introduce automat caractere care previn interpretarea unui
            //          sir de caractere ca o parte de comanda SQL (i.e. preventie
            //          atacuri de tip SQL injection)
            //mai eficient decat Statement
            PreparedStatement add_statement = connection.prepareStatement(s);

            add_statement.setInt(1, m.getId());
            add_statement.setString(2, m.getName());
            add_statement.setInt(3, m.getAge());
            add_statement.setString(4, m.getGenre());

            add_statement.executeUpdate();
        } catch (SQLException e) {
            //throw new RepoException cu mesajul erorii SQL
            throw new RepositoryException(e.getMessage());
        }

    }
    /// David Caval 321
    @Override
    public void remove(int id) {
        super.remove(id);
        String s = "DELETE FROM musicians WHERE id=?";
        //try-with-resources
        //https://docs.oracle.com/javase/8/docs/technotes/guides/language/try-with-resources.html
        try (PreparedStatement remove_statement = connection.prepareStatement(s)) {
            remove_statement.setInt(1, id);
            remove_statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Musician> getAll() {
        // = get all rows from the musician table
        List<Musician> resultList = new ArrayList<>();
        String s = "SELECT * FROM musicians";
        try (PreparedStatement getAllSstatement = connection.prepareStatement(s)) {
            ResultSet result = getAllSstatement.executeQuery();
            while (result.next()) {
                Musician m = new Musician(result.getInt("id"), result.getString("name"),
                        result.getInt("age"), result.getString("genre"));
                resultList.add(m);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
