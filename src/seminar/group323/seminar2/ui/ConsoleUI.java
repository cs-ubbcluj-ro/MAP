package seminar.group323.seminar2.ui;

import seminar.group323.seminar2.domain.Artist;
import seminar.group323.seminar2.repository.BinaryFileRepository;
import seminar.group323.seminar2.repository.IRepository;
import seminar.group323.seminar2.repository.Repository;
import seminar.group323.seminar2.repository.RepositoryException;
import seminar.group323.seminar2.service.ArtistService;

import java.io.IOException;


public class ConsoleUI {

    private ArtistService artistService;

    public ConsoleUI(ArtistService artistService) {
        this.artistService = artistService;
    }


    public static void main(String[] args) throws IOException, RepositoryException {


        IRepository<Artist> repo = null;

        String repoType = Settings.getInstance().getRepoType();
        String fileName = Settings.getInstance().getFileName();
        if ("memory".equals(repoType)) {
            repo = new Repository<>();
        }
        if ("binary".equals(repoType)) {
            repo = new BinaryFileRepository<>(fileName);
        }
        if (repo == null) {
            System.out.println("Eroare in fisierul de setari");
            return;
        }

        for (var artist : repo) {
            System.out.println(artist.getName());
        }
        repo.add(new Artist(103, "ABBA", "rap"));

//
//        Properties settings = new Properties();
////        settings.put("repo_type", "memory");
////        settings.store(new FileWriter("settings.properties"), "Fisier de setari pentru program");
//
//        settings.load(new FileReader("settings.properties"));
//        System.out.println(settings.get("repo_type"));


//        IRepository<Artist> repo = new Repository<>();
//        IRepository<Artist> repo = new BinaryFileRepository<>("artists.bin");


        ArtistService service = new ArtistService(repo);

//        try {
////            service.addArtist(100, "Michael Jackson", "pop");
////            service.addArtist(101, "Beyonce", "pop");
////            service.addArtist(102, "Mac Miller", "rap");
//        } catch (RepositoryException e) {
//            System.out.println("A aparut o problema: " + e.getMessage());
//            e.printStackTrace();
//        }


    }
}
