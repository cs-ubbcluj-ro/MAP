package Service;

import Domain.Player;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.RepositoryException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {
    private IRepository <Player> irepo;

    public PlayerService(IRepository<Player> irepo) {
        this.irepo = irepo;
    }

    public void adaugare(int id, String name, int age, String playerPosition) throws DuplicateEntityException, RepositoryException {
        Player p1 = new Player(id,name,age,playerPosition);
        ///validare player p1
        irepo.add(p1);
    }
    public void remove(int id) throws RepositoryException {
        irepo.remove(id);
    }

    public List<Player> sortPlayers(boolean reverse){
        return this.irepo.getAll()
                .stream()
                .sorted(reverse
                        ? Comparator.comparingInt(Player::getId).reversed() // Descending order
                        : Comparator.comparingInt(Player::getId)) // Ascending order)
                .collect(Collectors.toList());
    }

    public Player find(int id){
        return irepo.find(id);
    }
    public Collection<Player> getALL(){
        return irepo.getAll();
    }
}
