package seminar.group321.seminar4;

import seminar.group321.seminar4.domain.*;
import seminar.group321.seminar4.repository.*;
import seminar.group321.seminar4.service.MusicianService;
import seminar.group321.seminar4.ui.Console;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
//        IRepository<Musician> repoMusician;
//        IEntityFactory<Musician> musicianFactory = new MusicianFactory();
//        try {
//            repoMusician = new FileRepo<>("musicians.txt", musicianFactory);
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        IEntityFactory<Manager> managerFactory = new ManagerFactory();
//
//        IRepository<Manager> repoManager;
//        try {
//            repoManager = new FileRepo<>("managers.txt", managerFactory);
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        SQLMusicianRepository sqlMusicianRepository = new SQLMusicianRepository();
        MusicianService musicianService = new MusicianService(sqlMusicianRepository);
        Console console = new Console(musicianService);

        console.run();
        sqlMusicianRepository.closeConnection();
    }
}