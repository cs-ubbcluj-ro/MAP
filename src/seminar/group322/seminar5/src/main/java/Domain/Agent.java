package Domain;

import java.util.ArrayList;

public class Agent extends Persoana {
    String affilitiation;
    ArrayList<Player> managedPlayers;

    //add constructor
    //add a method to add a player to list of managed players
    public Agent(int id, String nume, int age, String affilitiation, ArrayList<Player> managedPlayers) {
        super(id, nume, age);
        this.affilitiation = affilitiation;
        this.managedPlayers = managedPlayers;
    }

    public Agent(int id, String nume, int age, String affilitiation) {
        super(id, nume, age);
        this.affilitiation = affilitiation;
        managedPlayers = new ArrayList<>();
    }

    public String getAffilitiation() {
        return this.affilitiation;
    }

    public ArrayList<Player> getManagedPlayers() {
        return this.managedPlayers;
    }

    public void addPlayer(Player p) throws Exception {
        if (managedPlayers.contains(p)) {
            throw new Exception("Exista deja player cu id dat in lista agentului " + this.name);
        } else {
            managedPlayers.add(p);
        }
    }
}
