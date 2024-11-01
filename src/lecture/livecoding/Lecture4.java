package lecture.livecoding;

import lecture.livecoding.domain.Rectangle;
import lecture.livecoding.domain.Shape2D;
import lecture.livecoding.domain.Square;
import lecture.livecoding.domain.UnitSquare;
import lecture.livecoding.repository.AbstractRepository;
import lecture.livecoding.repository.MemoryRepository;
import lecture.livecoding.repository.RepositoryException;

public class Lecture4 {
    public static void main(String[] args) {
        AbstractRepository<Shape2D> repo = new MemoryRepository<>();
        AbstractRepository<Rectangle> repoRectangles = new MemoryRepository<>();


        try {
            repo.add(new Rectangle(100, "rectangle one", 5, 4));
            repo.add(new Square(100, "rectangle one", 5));
            repo.add(UnitSquare.getInstance());
            repo.add(UnitSquare.getInstance());
        } catch (RepositoryException ex) {
            System.out.println("o exceptie");
        }


        for (var s : repo) {
            System.out.println(s);
        }

        Shape2D s = null;

    }
}
