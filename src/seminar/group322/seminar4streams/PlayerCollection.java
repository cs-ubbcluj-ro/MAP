package seminar.group322.seminar4streams;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerCollection {
    private String JDBC_URL = "jdbc:sqlite:D:/Facultate/Ore 2024-2025/MAP/Mine/SEMINAR4_Streams/sports_db";
    private Connection connection;

    private List<Player> players;

    public PlayerCollection() {
        this.players = new ArrayList<>();
        this.openConnection();
        this.loadEntities();

    }

    private void loadEntities() {
        String selectSQL = "SELECT * FROM players;";
        try (Statement statement = this.connection.createStatement()) {
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String playingPosition = rs.getString("playing_position");
                String nationality = rs.getString("nationality");
                String team = rs.getString("team");
                Player p = new Player(id, name, age, playingPosition, nationality, team);
                this.players.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);

        try {
            if (connection == null || connection.isClosed()) {
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Player> getAll() {
        return this.players;
    }

}
