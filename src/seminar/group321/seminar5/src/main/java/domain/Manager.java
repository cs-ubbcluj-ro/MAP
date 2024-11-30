package domain;

import java.util.ArrayList;

//Bivolaru Iustin
public class Manager extends Persoana {
    private String affiliation;
    private ArrayList<Musician> managedMusicians;

    public Manager(int id, String name, int age, String affiliation) {
        super(id, name, age);
        this.affiliation = affiliation;
        managedMusicians = new ArrayList<>();
    }

    public Manager(int id, String name, int age, String affiliation, ArrayList<Musician> managedMusicians) {
        super(id, name, age);
        this.affiliation = affiliation;
        this.managedMusicians = managedMusicians;
    }

    public void addMusician(Musician m) {
        managedMusicians.add(m);

    }

    public String getAffiliation(){
        return this.affiliation;
    }

    public ArrayList<Musician> getManagedMusicians(){
        return this.managedMusicians;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") + ", affiliation=" + affiliation + "}";
    }
}
