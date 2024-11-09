package seminar.group322.seminar3;

import seminar.group322.seminar3.Domain.*;
import seminar.group322.seminar3.Repository.*;
import seminar.group322.seminar3.Service.PlayerService;
import seminar.group322.seminar3.UI.Console;

public class Main {

    public static void main(String[] args) {
        //IRepository<Player> playersRepo = new Repository<Player>();


        IEntityFactory<Player> playerFactory = new PlayerFactory();
        IEntityFactory<Agent> managerFactory = new AgentFactory();
        IRepository<Player> playersRepo2;

        try {
            playersRepo2 = new FileRepo<Player>("players.txt", playerFactory);
            //playersRepo2 = new BinaryFileRepository<Player>("players.bin");
            PlayerService playerService = new PlayerService(playersRepo2);
            Console console = new Console(playerService);
            console.run();
        } catch (RepositoryException e) {
            System.out.println("Nu s-a putut porni aplicatia, exista urmatoarele probleme cu datele din fisier:");
            System.out.println(e.getMessage());
        }

        try {
            IRepository<Agent> agentRepo = new FileRepo<Agent>("managers.txt", managerFactory);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }



    }


}