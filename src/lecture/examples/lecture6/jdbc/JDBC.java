package lecture.examples.lecture6.jdbc;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

class Book {
    private final String authors;
    private final String title;
    private final int numberOfPages;

    public Book(String authors, String title, int numberOfPages) {
        this.authors = authors;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors='" + authors + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    public String getAuthors() {
        return authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getTitle() {
        return title;
    }
}

public class JDBC {

    private static final String JDBC_URL =
            "jdbc:sqlite:src/lecture/examples/lecture6/jdbc/test.db";

    private Connection conn = null;

    /**
     * Gets a connection to the database.
     * If the underlying connection is closed, it creates a new connection. Otherwise, the current instance is returned.
     */
    private void openConnection() {
        try {
            // with DriverManager
//            if (conn == null || conn.isClosed())
//                conn = DriverManager.getConnection(JDBC_URL);

            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the underlying connection to the in-memory SQLite instance.
     */
    private void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the sample schema for the database.
     */
    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books(authors varchar(400), title varchar(200), pages int);");
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sales(title varchar(400), number_of_books int, total int);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    /**
     * Adds some entries in the table books;
     */
    void initTables() {
        final String[] books = new String[]{
                "Heather Morris|The Tattooist of Auschwitz|250",
                "Andre Agassi|Open|300",
                "Aaron Courville, Ian Goodfellow, Yoshua Bengio|Deep Learning|700"
        };

        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO books VALUES (?, ?, ?)")) {
                for (String s : books) {
                    String[] tokens = s.split("[|]");
                    statement.setString(1, tokens[0]);
                    statement.setString(2, tokens[1]);
                    statement.setInt(3, Integer.parseInt(tokens[2]));
                    statement.executeUpdate();
                }
            }

            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO sales VALUES (?, ?, ?)")) {
                statement.setString(1, "Open");
                statement.setInt(2, 0);
                statement.setInt(3, 0);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add the given book to the database.
     */
    void addBook(Book b) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO books VALUES (?, ?, ?)")) {
                statement.setString(1, b.getAuthors());
                statement.setString(2, b.getTitle());
                statement.setInt(3, b.getNumberOfPages());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove the book with the given title from table books.
     *
     * @param title
     */
    void removeBookByTitle(String title) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM books WHERE title=?")) {
                statement.setString(1, title);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all book from table books in the database.
     *
     * @return: an ArrayList with Book objects.
     */
    ArrayList<Book> getAll() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from books"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Book b = new Book(rs.getString("authors"), rs.getString("title"),
                            rs.getInt("pages"));
                    books.add(b);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return books;
    }

    void updateSalesTransaction() {
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement updateSales = conn.prepareStatement("UPDATE sales SET number_of_books = ? WHERE title = ?");
                 PreparedStatement updateTotal = conn.prepareStatement("UPDATE sales SET total = total + ? WHERE title = ?")) {
                updateSales.setInt(1, 20);
                updateSales.setString(2, "Open");
                updateSales.executeUpdate();

                int bookPrice = 55;
                updateTotal.setInt(1, 20 * bookPrice);
                updateTotal.setString(2, "Open");
                updateTotal.executeUpdate();

                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBC db_example = new JDBC();
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();

        Book b = new Book("Jordan Ellenberg", "How not to be wrong. The hidden maths of everday life", 470);
        db_example.addBook(b);

        db_example.removeBookByTitle("Deep Learning");

        db_example.updateSalesTransaction();

        ArrayList<Book> booksList = db_example.getAll();
        for (Book book : booksList)
            System.out.println(book);

        db_example.closeConnection();
    }
}
