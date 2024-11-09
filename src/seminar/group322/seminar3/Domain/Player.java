package seminar.group322.seminar3.Domain;

//Petian Simona

public class Player extends Persoana {
    String playerPosition;

    public Player(int id, String name, int age, String playerPosition) {
        super(id, name, age);
        this.playerPosition = playerPosition;
    }

    public String getPlayingPosition() {
        return playerPosition;
    }

    public String toString() {
        return super.toString().replace("}", "")
                + ", playingPosition="
                + playerPosition + "}";
    }


}
