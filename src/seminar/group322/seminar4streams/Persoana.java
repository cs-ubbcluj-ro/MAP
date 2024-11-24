package seminar.group322.seminar4streams;

import java.util.Objects;

//Pasca Gabriel
public class Persoana extends Entity{
    String name;
    int age;
    public Persoana(int id,String name, int age){
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

    @Override
    public String toString() {
        //modificat fata de seminar: this.getClass() in loc de Persoana scris direct,
        //pentru a ni se printa clasa de care apartine fiecare instanta pentru care
        //se apeleaza toString
        return this.getClass()+"{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return super.id == persoana.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
