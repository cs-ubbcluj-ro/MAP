package seminar.group322.seminar3.Service;

import seminar.group322.seminar3.Domain.Player;
import seminar.group322.seminar3.Repository.*;

import java.util.Collection;

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
    public Collection<Player> getALL(){
        return irepo.getAll();
    }
}
