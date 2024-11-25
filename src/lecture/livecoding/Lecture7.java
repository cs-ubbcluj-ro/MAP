package lecture.livecoding;

import lecture.livecoding.domain.UnitSquare;
import lecture.livecoding.repository.RepositoryException;
import lecture.livecoding.repository.SQLSquareRepository;

public class Lecture7 {
    public static void main(String[] args) throws RepositoryException {
        try (SQLSquareRepository repo = new SQLSquareRepository()) {
            repo.add(UnitSquare.getInstance());

            for (var sq : repo) {
                System.out.println(sq);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
