package seminar.group322.seminar3.UI;

import seminar.group322.seminar3.Domain.Player;
import seminar.group322.seminar3.Repository.RepositoryException;
import seminar.group322.seminar3.Service.PlayerService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    PlayerService playerService;

    public Console(PlayerService service) {
        this.playerService = service;
    }

    public void printMenu() {
        System.out.println("1. Adauga player");
        System.out.println("2. Afiseaza toti playerii");
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
                    addplayerUI();
                    break;
                case 2:
                    printAll(playerService.getALL());
                    break;
                case 3:
                    isRunning = false;

            }
        }
    }

    private void printAll(Collection<Player> all) {
        for (Player m : all) {
            System.out.println(m);
        }
    }

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

    private void addplayerUI() {
        Scanner scanner = new Scanner(System.in);
        int id, age;
        String name, playingPosition;
        System.out.println("ID:");
        id = readInt(scanner);
        System.out.println("Name:");
        name = scanner.next();
        System.out.println("Age:");
        age = readInt(scanner);
        System.out.println("Playing position");
        playingPosition = scanner.next();
        try {
            playerService.adaugare(id, name, age, playingPosition);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }

    }
}
