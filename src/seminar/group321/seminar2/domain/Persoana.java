package seminar.group321.seminar2.domain;

//Roger Bostan - cod
//Alexandrescu Emilia - raspunsuri
//Caval David - raspunsuri


import java.util.Objects;

public class Persoana extends Entity {
    protected String name;
    protected int age;

    public Persoana(int id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    //Adaugat in urma discutiei de pe finalul seminarului:
    //Exemplu de suprascriere equals()
    //Consideram doua obiecte de tip Persoana egale
    //daca au acelasi id, nume si varsta
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Persoana persoana = (Persoana) o;
        return id == persoana.id && age == persoana.age && name.equals(persoana.name);
    }

    //Vom discuta la seminarul urmator de ce trebuie sa suprascriem si hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return this.getClass() + "{id=" + id + ", name=" + name + ", age=" + age + "}";
    }
}
