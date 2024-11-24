package seminar.group322.seminar4.Service;

import seminar.group322.seminar4.Domain.Player;
import seminar.group322.seminar4.Repository.DuplicateEntityException;
import seminar.group322.seminar4.Repository.IRepository;
import seminar.group322.seminar4.Repository.RepositoryException;
import seminar.group322.seminar4.Domain.Player;
import seminar.group322.seminar4.Repository.IRepository;

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
    public void remove(int id) throws RepositoryException {
        irepo.remove(id);
    }

    public Player find(int id){
        return irepo.find(id);
    }
    public Collection<Player> getALL(){
        return irepo.getAll();
    }
}
