package lecture.examples.lecture3.generics;

import lecture.examples.lecture3.Person;


class Student extends Person {
    private int yearOfStudy;

    Student(String name, int yearOfStudy) {
        super(name);
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public String toString() {
        return "Student{" +
                "yearOfStudy=" + yearOfStudy +
                "} " + super.toString();
    }
}

class StudentWithScolarship extends Student {
    private int amount;

    StudentWithScolarship(String name, int yearOfStudy, int amount) {
        super(name, yearOfStudy);
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "StudentWithScolarship{" +
                "amount=" + amount +
                "} " + super.toString();
    }
}

public class Wildcards {
    public static void printElements(LinkedList<?> list)
    // when we want to be able to use any type
    {
        LinkedList<?>.ListIterator<?> iterator = list.iterator();
        while (iterator.valid()) {
            System.out.println(iterator.element());
            iterator.next();
        }
    }

    public static void printElementsUpperBounded(
            LinkedList<? extends Person> list) // it can accept all object who have IS-A relationship with Person
    {
        LinkedList<?>.ListIterator<?> iterator = list.iterator();
        while (iterator.valid()) {
            System.out.println(iterator.element());
            iterator.next();
        }
    }

    public static void printElementsLowerBounded(
            LinkedList<? super Student> list) // it can accept all objects that are above Student in the type hierarchy
    {
        LinkedList<?>.ListIterator<?> iterator = list.iterator();
        while (iterator.valid()) {
            System.out.println(iterator.element());
            iterator.next();
        }
    }

    public static void main(String[] args) {
        LinkedList<String> l1 = new LinkedList<>();
        l1.add(new String("one"));
        l1.add(new String("two"));
        l1.add(new String("three"));
        printElements(l1);
        System.out.println("-----------------------------------------------");

        LinkedList<Person> l2 = new LinkedList<>();
        l2.add(new Person("Ana"));
        l2.add(new Person("Bianca"));
        l2.add(new Person("Vlad"));
        printElements(l2);
        System.out.println("-----------------------------------------------");

        LinkedList<Student> l3 = new LinkedList<>();
        l3.add(new Student("Ana", 2));
        l3.add(new Student("Bianca", 1));
        l3.add(new Student("Vlad", 3));
        printElementsUpperBounded(l2);
        printElementsUpperBounded(l3);
        //printElementsUpperBounded(l1); // ERROR: String does not inherit from Person!
        System.out.println("-----------------------------------------------");

        printElementsLowerBounded(l3);
        printElementsLowerBounded(l2);
        LinkedList<StudentWithScolarship> l4 = new LinkedList<>();
        l4.add(new StudentWithScolarship("Ana", 2, 700));
        l4.add(new StudentWithScolarship("Vlad", 3, 700));

        printElementsUpperBounded(l4);

//        printElementsLowerBounded(l4); // ERROR
    }
}
