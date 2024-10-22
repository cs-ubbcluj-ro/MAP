package seminar.group323.seminar2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seminar.group323.seminar2.domain.Artist;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtistTest {

    private Artist johnny;

    @BeforeEach
    public void createJohnny() {
        johnny = new Artist(100, "Johnny Bravo", "populara");
    }

    @BeforeEach
    public void createJohnny2() {
        johnny = new Artist(100, "Johnny Bravo", "populara");
    }

    @Test
    public void testArtistId() {
        // testare folosind assert, ca semestrul trecut
//        assert a.getId() == 101;

        // testare conform cu JUnit
        assertEquals(101, johnny.getId());
    }

    @Test
    public void testArtist() {
        assertEquals("Johnny Bravo", johnny.getName());
        assertEquals("populara", johnny.getGenre());
    }
}
