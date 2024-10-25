package lecture.livecoding.tests;

import lecture.livecoding.domain.Rectangle;
import lecture.livecoding.domain.Shape;
import lecture.livecoding.domain.UnitSquare;
import lecture.livecoding.repository.MemoryRepository;
import lecture.livecoding.repository.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryRepositoryTest {

    @Test
    public void testRepository() {
        MemoryRepository<Rectangle> repo = new MemoryRepository<>();
        try {
            repo.add(UnitSquare.getInstance());
            Shape s = repo.find(1);

            // in repo a fost adaugat patratul unitate
            Assertions.assertEquals(UnitSquare.getInstance(), s);

        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        Shape s = repo.find(2);
        Assertions.assertNull(s);
    }

}
