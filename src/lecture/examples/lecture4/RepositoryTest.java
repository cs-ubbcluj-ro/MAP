package lecture.examples.lecture4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryTest {

    private Repository repo;

    @BeforeEach
    void setUp() {
        repo = new Repository();
        repo.add(new Book("a", "b", 10));
        repo.add(new Book("c", "d", 20));
        repo.add(new Book("e", "f", 25));
    }

    @Test
    void add() {
        assertEquals(3, repo.size());
        repo.add(new Book("f", "e", 10));
        assertEquals(4, repo.size());
    }

    @Test
    void remove() {
        repo.remove(new Book("e", "f", 25));
        assertEquals(2, repo.size());
    }

    @Test
    void size() {
        assertEquals(3, repo.size());
        repo.add(new Book("a", "c", 20));
        assertEquals(4, repo.size());
    }
}