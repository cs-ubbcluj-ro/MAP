package seminar.group322.exampleequalshash;

import java.util.Objects;

public class Person {
    int cnp;
    String nume;

    public Person(int cnp, String name) {
        this.cnp = cnp;
        this.nume = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return cnp == person.cnp && Objects.equals(nume, person.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnp, nume);
    }

    @Override
    public String toString() {
        return "Person{" +
                "cnp=" + cnp +
                ", nume='" + nume + '\'' +
                '}';
    }
}
