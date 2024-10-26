package seminar.group322.seminar2;


import seminar.group322.seminar2.Domain.Agent;
import seminar.group322.seminar2.Domain.Persoana;
import seminar.group322.seminar2.Domain.Player;
import seminar.group322.seminar2.Repository.Repository;

public class Main {
    /*
   1. Clasa abstracta Entity, camp ID de tip int - done
       - ID este camp protected
       - ID-ul se poate seta prin constructorul clasei
       - metoda publica prin care se poate accesa ID: getID()

   2. Clasa Persoana care este derivata din Entity - done
       - campuri: name (String), age (int)

   3. Clasa FootballPlayer derivata din Persoana - done
       - campuri: playingPosition (String) e.g. midfielder, defender, striker

   4. Clasa Agent derivata din Persoana - done
       -campuri: affiliation (String), managedPlayers (ArrayList<FootballPlayer>)

   5. Clasa repository.repository.Repository<T> generica (T va fi fie Player, fie Agent) - done
       -camp: ArrayList<T> in care se stocheaza entitati din domeniu
       -posibiltate de:
           --adaugare entitate - done
           --cautare entitate dupa ID - done
           --stergere entitate - done
       -make it nice, make it implement the Iterable interface (+implement our own iterator) - not done;
                indicatii se pot gasi in cod Seminar 2 la grupa 321 (clasa Repository)
    */
    public static void main(String[] args) {
        Persoana p = new Persoana(1, "Ion", 23);
        System.out.println(p);

        Player p1 = new Player(2, "Petre", 27, "defender");
        System.out.println(p1);
        Player p2 = new Player(2, "ABC", 19, "striker");
        System.out.println(p2);
        Player p3 = new Player(8, "Sebastian", 23, "striker");
        Player p4 = new Player(5, "Stefan", 37, "midfielder");

        Agent a = new Agent(3, "Viorel", 46, "ABC");

        try {
            System.out.println("Incercam sa adaugam player-ul p1 la agentul a:");
            a.addPlayer(p1);
            System.out.println("Player p1 s-a adaugat cu succes.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Incercam sa adaugam player-ul p1 la agentul a " +
                    "(din nou, ar trebui sa ni se spuna ca mai exista):");
            a.addPlayer(p1);
            System.out.println("Player p1 s-a adaugat cu succes.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Incercam sa adaugam player-ul p2 la agentul a (ar trebui sa ni se spuna " +
                    "ca mai exista, p1 si p2 au acelasi id):");
            a.addPlayer(p2);
            System.out.println("Player p2 s-a adaugat cu succes.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Repository<Player> playerRepo = new Repository<Player>();
        Repository<Agent> agentRepo = new Repository<Agent>();

        try {
            System.out.println("Incercam sa adaugam p1 la repo:");
            playerRepo.addEntity(p1);
            //p2 nu ar trebui sa se adauge, are acelasi id cu p1
            System.out.println("Incercam sa adaugam p2 la repo (p2.id==p1.id)");
            playerRepo.addEntity(p2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Incercam sa adaugam agent la agentRepo:");
            agentRepo.addEntity(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(playerRepo);
        System.out.println(agentRepo);

        Player p5 = new Player(10, "Hagi", 27, "midfielder");
        try {
            playerRepo.addEntity(p5);
            System.out.println("Adaugat player " + p5 + " la repository. Repo arata asa acum:");
            System.out.println(playerRepo);
            playerRepo.removeEntity(10);
            System.out.println("Sters player " + p5 + " din repository. Repo arata asa acum:");
            System.out.println(playerRepo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}