package seminar.group322.seminar4.Repository;

import seminar.group322.seminar4.Domain.Player;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerDbRepository extends Repository<Player> {
    private Connection connection;
    private String DB_URL = "jdbc:sqlite:src/seminar/group322/seminar4/sports_db";
    public PlayerDbRepository() throws RepositoryException {
        openConnection();
        createTable();
        loadData();
    }

    //added from seminar - load data when we start app
    private void loadData() {
        Collection<Player> players = getAll();
        entities.addAll(players);
    }

    private void openConnection() throws RepositoryException {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(DB_URL);

        try {
            if (connection == null || connection.isClosed()) {
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void add(Player player) throws RepositoryException {
        super.add(player);
        String insertSQL = "INSERT INTO players VALUES (?, ?, ?, ?);";
        //try with resources
        //https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
            insertStatement.setInt(1, player.getId());
            insertStatement.setString(2, player.getName());
            insertStatement.setInt(3, player.getAge());
            insertStatement.setString(4, player.getPlayingPosition());
            int numberOfRowsAffected = insertStatement.executeUpdate();
            System.out.println("Number of rows affected in add:" + numberOfRowsAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(int id) throws NonExistEntity {
        super.remove(id);
        String deleteSQL = "DELETE FROM players WHERE id=(?)";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
            deleteStatement.setInt(1, id);
            int numberOfRowsAffected = deleteStatement.executeUpdate();
            System.out.println("Number of rows affected in delete:" + numberOfRowsAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Player find(int id) {
        String findSQL = "SELECT * FROM players WHERE id = (?);";
        try (PreparedStatement findStatement = connection.prepareStatement(findSQL)) {
            findStatement.setInt(1, id);
            ResultSet rs = findStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String playingPos = rs.getString("playingPos");
                Player p = new Player(id, name, age, playingPos);
                return p;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private void createTable() {
        String createSQL = "CREATE TABLE IF NOT EXISTS players (" +
                "id int, name varchar(100), age int, playingPos varchar(20)," +
                "PRIMARY KEY(id));";
        try {
            Statement createStatement = connection.createStatement();
            createStatement.execute(createSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initData() {
        //method to initialize data in table
        List<Player> playersToAdd = new ArrayList<>();
        playersToAdd.add(new Player(2, "Andrei", 31, "defender"));
        playersToAdd.add(new Player(3, "Alin", 27, "goalkeeper"));
        playersToAdd.add(new Player(4, "Adrian", 18, "midfielder"));
        playersToAdd.add(new Player(5, "Valentin", 29, "forward"));
        playersToAdd.add(new Player(6, "Denis", 35, "defender"));

        String insertSQL = "INSERT INTO players VALUES (?, ?, ?, ?);";
        //change into try-with-resources
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
            for (Player player : playersToAdd) {
                insertStatement.setInt(1, player.getId());
                insertStatement.setString(2, player.getName());
                insertStatement.setInt(3, player.getAge());
                insertStatement.setString(4, player.getPlayingPosition());
                int noRowsAffected = insertStatement.executeUpdate();
                System.out.println("Number of rows affected:" + noRowsAffected);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Player> getAll() {
        List<Player> allPlayers = new ArrayList<>();
        String selectSQL = "SELECT * FROM players;";
        //try with resources
        try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String playingPos = rs.getString("playingPos");
                Player p = new Player(id, name, age, playingPos);
                allPlayers.add(p);
            }
            return allPlayers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
