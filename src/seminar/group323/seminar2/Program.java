package seminar.group323.seminar2;

import seminar.group323.seminar2.domain.Artist;
import seminar.group323.seminar2.repository.IRepository;
import seminar.group323.seminar2.repository.Repository;
import seminar.group323.seminar2.repository.RepositoryException;

public class Program {
    /**
     * domain
     * clasa abstracta Entity
     * id : int
     * <p>
     * clasa Person extends Entity
     * name : String
     * <p>
     * clasa Artist : Person
     * gen : String
     * <p>
     * clasa Producer : Person
     * artists : List<Artist>
     * <p>
     * repository
     * clasa MemoryRepository
     * <p>
     * service
     * clasa MusicService
     * <p>
     * ui
     * clasa ConsoleUI
     */
    public static void main(String[] args) {

        IRepository<Artist> repo = new Repository<Artist>();
        try {
            repo.add(new Artist(101, "Johnny Bravo", "populara"));
            Artist a = repo.findById(102);
        } catch (NumberFormatException e) {
            System.out.println("oh oh!");
        } catch (RepositoryException e) {
            System.out.println("oh oh!");
        }


    }
}
