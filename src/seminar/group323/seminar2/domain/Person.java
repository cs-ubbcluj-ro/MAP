package seminar.group323.seminar2.domain;

public class Person extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int id, String name) {
        super(id);
        this.name = name;
    }
}
