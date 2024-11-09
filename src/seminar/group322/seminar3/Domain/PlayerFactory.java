package seminar.group322.seminar3.Domain;

public class PlayerFactory implements IEntityFactory<Player> {
    @Override
    public Player createEntity(String line) {
        String[] items = line.split(",");
        //System.out.println(line);
        int id = Integer.parseInt(items[0].strip());
        String nume = items[1].strip();
        int varsta = Integer.parseInt(items[2].strip());
        String playingPosition = items[3].strip();

        return new Player(id, nume, varsta, playingPosition);

    }
}
