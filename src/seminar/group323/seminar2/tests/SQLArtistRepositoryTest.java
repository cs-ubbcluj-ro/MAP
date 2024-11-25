package seminar.group323.seminar2.tests;

import org.junit.jupiter.api.Test;
import seminar.group323.seminar2.domain.Artist;
import seminar.group323.seminar2.repository.RepositoryException;
import seminar.group323.seminar2.repository.SQLArtistRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLArtistRepositoryTest {
    private SQLArtistRepository repo = new SQLArtistRepository("src/seminar/group323/seminar2/tests/artists.db");


    @Test
    public void testSQLRepository() {
        assertEquals(repo.getAll().size(), 0);
        try {
            repo.add(new Artist(100, "ABCD", "painter"));
            repo.add(new Artist(101, "ABCD", "painter"));
            repo.add(new Artist(102, "ABCD", "painter"));
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(repo.getAll().size(), 3);
    }
}
