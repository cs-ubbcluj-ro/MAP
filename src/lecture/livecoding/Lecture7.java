package lecture.livecoding;

import lecture.livecoding.domain.Square;
import lecture.livecoding.domain.UnitSquare;
import lecture.livecoding.repository.RepositoryException;
import lecture.livecoding.repository.SQLRepository;

public class Lecture7 {
    public static void main(String[] args) throws RepositoryException {
        try (SQLRepository<Square> repo = new SQLRepository<>()) {
            repo.add(UnitSquare.getInstance());

            for (var sq : repo) {
                System.out.println(sq);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
