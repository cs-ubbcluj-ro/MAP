package seminar.group321.seminar3;

import seminar.group321.seminar3.domain.*;
import seminar.group321.seminar3.repository.DuplicateEntityException;
import seminar.group321.seminar3.repository.FileRepo;
import seminar.group321.seminar3.repository.IRepository;
import seminar.group321.seminar3.service.MusicianService;
import seminar.group321.seminar3.ui.Console;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        IRepository<Musician> repoMusician;
        //= new Repository<>();
        IEntityFactory<Musician> musicianFactory = new MusicianFactory();
        try {
            repoMusician = new FileRepo<>("musicians.txt", musicianFactory);
            MusicianService musicianService = new MusicianService(repoMusician);
            Console console = new Console(musicianService);
            console.run();

        } catch (DuplicateEntityException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        IEntityFactory<Manager> managerFactory = new ManagerFactory();

        IRepository<Manager> repoManager;
        try {
            repoManager = new FileRepo<>("managers.txt", managerFactory);
        } catch (DuplicateEntityException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}