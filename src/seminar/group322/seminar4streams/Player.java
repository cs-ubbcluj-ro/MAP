package seminar.group322.seminar4streams;

//Petian Simona

public class Player extends Persoana {
    String playerPosition;
    String nationality;
    String team;

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Player(int id, String name, int age, String playerPosition, String nationality, String team) {
        super(id, name, age);
        this.playerPosition = playerPosition;
        this.nationality = nationality;
        this.team = team;
    }


    @Override
    public String toString() {
        return "Player{" +
                "playerPosition='" + playerPosition + '\'' +
                ", nationality='" + nationality + '\'' +
                ", team='" + team + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
