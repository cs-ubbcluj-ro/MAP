package seminar.group322.seminar4;

import seminar.group322.seminar4.Domain.*;
import seminar.group322.seminar4.Repository.*;
import seminar.group322.seminar4.Service.PlayerService;

public class Main {

    public static void main(String[] args) {
        //IRepository<Player> playersRepo = new Repository<Player>();
        IRepository<Agent> agentRepo = new Repository<Agent>();


        IEntityFactory<Player> playerFactory = new PlayerFactory();
        IEntityFactory<Agent> managerFactory = new AgentFactory();
        //IRepository<Player> playersRepo2;

        try {
            //playersRepo2 = new FileRepo<Player>("players.txt", playerFactory);
            //playersRepo2 = new BinaryFileRepository<Player>("players.bin");
            PlayerDbRepository playersRepo2 = new PlayerDbRepository();
            PlayerService playerService = new PlayerService(playersRepo2);

            Console console = new Console(playerService);
            console.run();
            playersRepo2.closeConnection();
        } catch (RepositoryException e) {
            System.out.println("Nu s-a putut porni aplicatia, exista urmatoarele probleme cu datele din fisier:");
            System.out.println(e.getMessage());
        }





    }


}