package lecture.livecoding;

import lecture.livecoding.domain.Square;
import lecture.livecoding.domain.SquareConverter;
import lecture.livecoding.domain.UnitSquare;
import lecture.livecoding.repository.*;
import lecture.livecoding.util.Settings;

public class Lecture6 {

    public static AbstractRepository<Square> getRepository() {
        Settings s = Settings.getInstance();

        if ("memory".equals(s.getRepoType())) {
            return new MemoryRepository<Square>();
        } else if ("binary".equals(s.getRepoType())) {
            return new BinaryFileRepository<Square>(s.getFileName());
        } else if ("text".equals(s.getRepoType())) {
            return new TextFileRepository<Square>(s.getFileName(), new SquareConverter());
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit!");
    }

    public static void main(String[] args) {
        try {
            AbstractRepository<Square> repo = getRepository();
            repo.add(UnitSquare.getInstance());
            repo.add(new Square(101, "unu", 5));
            for (var s : repo) {
                System.out.println(s);
            }
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }
}
