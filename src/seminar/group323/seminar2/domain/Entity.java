package seminar.group323.seminar2.domain;

public abstract class Entity {
    // id private inseamna ca aceasta valoare nu poate
    // fi modificata dupa prima atribuire
    private final int id; // int este initializat cu 0

    public Entity(int id) {
        // apeleaza constructorul implicit Object()
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
