package seminar.group322.exampleequalshash;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1234, "ABC");
        Person p2 = new Person(1234, "ABC");
        System.out.println("Are p1 and p2 equal? ");
        System.out.println(p1.equals(p2));


        HashSet<Person> personSet = new HashSet<>();
        boolean isAdded = personSet.add(p1);
        System.out.println("p1 has been added?");
        System.out.println(isAdded);
        System.out.println("Set looks like this:" + personSet);

        boolean isAdded2 = personSet.add(p2);
        System.out.println("p2 has been added?");
        System.out.println(isAdded2);
        System.out.println("Set looks like this:" + personSet);

    }
}