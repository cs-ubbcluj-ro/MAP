package seminar.group323.seminar2;

import seminar.group323.seminar2.domain.Artist;
import seminar.group323.seminar2.repository.SQLArtistRepository;

import java.util.Collection;

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


        try (var repo = new SQLArtistRepository("src/seminar/group323/seminar2/artists.db")) {
//            repo.add(new Artist(102, "Johnny Bravo", "populara"));
//            repo.remove(102);
            var art = repo.findById(102);
            System.out.println(art.getName());

//            Collection<Artist> data = repo.getAll();
//            for (var artist : data) {
//                System.out.println(artist.getId() + " " + artist.getName());
//            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        try {
//            repo.add(new Artist(101, "Johnny Bravo", "populara"));
//            Artist a = repo.findById(102);
//        } catch (NumberFormatException e) {
//            System.out.println("oh oh!");
//        } catch (RepositoryException e) {
//            System.out.println("oh oh!");
//        }


    }
}
