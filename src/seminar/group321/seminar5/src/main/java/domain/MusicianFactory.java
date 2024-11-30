package domain;

public class MusicianFactory implements IEntityFactory<Musician>{
    @Override
    public Musician createEntity(String line) {
        String[] campuri = line.split(",");
        int id = Integer.parseInt(campuri[0]);
        String name = campuri[1];
        int age = Integer.parseInt(campuri[2]);
        String genre = campuri[3];
        return new Musician(id, name, age, genre);
    }
}
