package seminar.group321.seminar3.ui;

import seminar.group321.seminar3.domain.Musician;
import seminar.group321.seminar3.repository.DuplicateEntityException;
import seminar.group321.seminar3.service.MusicianService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    MusicianService musicianService;

    public Console(MusicianService service) {
        this.musicianService = service;
    }

    public void printMenu() {
        System.out.println("1. Adauga musician");
        System.out.println("2. Afiseaza toti musicians");
        System.out.println("3. Exit");
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.println(">>>");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addMusicianUI();
                    break;
                case 2:
                    printAll(musicianService.getAll());
                    break;
                case 3:
                    isRunning = false;

            }
        }
    }

    //Cum am putea face sa folosim aceasta metoda pentru
    //a afisa ambele tipuri de entitati?
    //i.e. sa pot apela si printAll(collectionOfMusicians)
    //si printAll(collectionOfManagers)
    private void printAll(Collection<Musician> all) {
        for (Musician m : all) {
            System.out.println(m);
        }
    }

    //aceasta metoda poate fi mutata intr-un utils
    private int readInt(Scanner scanner) {
        int intToRead;
        while (true) {
            try {
                intToRead = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                scanner.next();
                System.out.println("Please insert a number.");
            }
        }
        return intToRead;
    }

    private void addMusicianUI() {
        Scanner scanner = new Scanner(System.in);
        int id, age;
        String name, genre;
        System.out.println("ID:");
        id = readInt(scanner);
        System.out.println("Name:");
        name = scanner.next();
        System.out.println("Age:");
        age = readInt(scanner);
        System.out.println("Genre");
        genre = scanner.next();
        try {
            musicianService.add(id, name, age, genre);
        } catch (DuplicateEntityException e) {
            System.out.println(e.getMessage());
        }

    }
}
