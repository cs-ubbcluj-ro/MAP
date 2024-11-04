package lecture.examples.lecture6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

@FunctionalInterface
interface Formula {
    double executeOperation(double a, double b);
}

@FunctionalInterface
interface Capitalize {
    String capitalize(String s);
}

@FunctionalInterface
interface StudentFactory<T extends Student> {
    T create(int id, String name, double average);
}

public class Examples {
    public static void main(String[] args) {
        Formula addition = (a, b) -> a + b;
        Formula multiplication = (a, b) -> a * b;

        double a = 2.2;
        double b = 3.3;
        System.out.println("Sum: " + addition.executeOperation(a, b));
        System.out.println("Product: " + multiplication.executeOperation(a, b));

        // Function interface
        Function<Double, Double> square = (x) -> x * x;
        System.out.println("Square of 5: " + square.apply((double) 5));

        Function<Integer, Double> half = (x) -> (double) x / 2;
        System.out.println("Half of 5: " + half.apply(5));

        // Predicate interface
        Predicate<Integer> isEven = (x) -> x % 2 == 0;
        System.out.println((isEven.test(10)) ? "10 is even" : "10 is not even");

        Predicate<Student> hasPassed = (s) -> s.getAverage() >= 5;
        List<Student> students = new ArrayList<>();
        Student s1 = new Student(1, "Andrei", 7.3);
        Student s2 = new Student(2, "Ioana", 4.8);
        Student s3 = new Student(3, "Cristina", 9.75);
        Student s4 = new Student(4, "Ana", 6.25);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        System.out.println("Students situation: ");
        for (Student s : students)
            System.out.println(hasPassed.test(s) ? "Student " + s.getName() + " has passed." : "Student " + s.getName() + " has not passed.");
        System.out.println("----------------------------------------------------------------------------------");

        System.out.println("Students who have passed and " +
                "whose names start with 'A': ");
        Predicate<Student> hasPassedAndNameA =
                hasPassed.and(x -> x.getName().startsWith("A"));
        for (Student s : students) {
            if (hasPassedAndNameA.test(s))
                System.out.println(s);
        }
        System.out.println("----------------------------------------------------------------------------------");

        // UnaryOperator
        UnaryOperator<Student> unaryOperator =
                (s) -> {
                    s.setName("Mary Poppins");
                    return s;
                };
        s3 = unaryOperator.apply(s3);
        System.out.println(s3);

        // BinaryOperator
        BinaryOperator<Integer> binaryOperator =
                (i1, i2) -> {
                    i1 = i1 - 100 * i2;
                    return i1;
                };
        System.out.println("Binary operator result: " +
                binaryOperator.apply(500, 2));

        BinaryOperator<Student> op =
                BinaryOperator.minBy(
                        (st1, st2) -> (st1.getAverage() > st2.getAverage()) ? 1 : ((st1.getAverage() == st2.getAverage()) ? 0 : -1));
        System.out.println("Comparing " + s3 + " with " + s4 + " by average, the student with the lowest average is: " + op.apply(s3, s4));
        System.out.println("----------------------------------------------------------------------------------");

        // Supplier
        Supplier<Double> randomValueSupplier = () -> Math.random();
        System.out.println("Some random values: ");
        for (int i = 0; i < 10; i++)
            System.out.println(randomValueSupplier.get());
        System.out.println();

        Supplier<Student> studentSupplier = () -> {
            Random random = new Random();
            String[] capitals =
                    new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            String[] lower =
                    new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "Z"};
            return new Student(random.nextInt(20), capitals[random.nextInt(26)] + lower[random.nextInt(26)] + lower[random.nextInt(26)], 10 * Math.random());
        };
        System.out.println("Some students with random names and averages: ");
        for (int i = 0; i < 10; i++)
            System.out.println(studentSupplier.get());
        System.out.println("----------------------------------------------------------------------------------");

        // Consumer
        Consumer<Integer> consumer = (x) -> System.out.println(x);
        System.out.println("Printing with the consumer: ");
        consumer.accept(10);

        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        // Referring method to String type Consumer interface
        Consumer<List<Integer>> listConsumer = (l) -> {
            int sum = 0;
            for (Integer i : l)
                sum += i;
            System.out.println("The sum is: " + sum);
        };
        listConsumer.accept(list);  // Calling Consumer method

        System.out.println("----------------------------------------------------------------------------------");
        // Reference to a static method
        List<Object> objects = Arrays.asList(2, s1, 3.4f, s2);
        objects.forEach(System.out::println); // public void forEach(Consumer<? super E> action)
        System.out.println();

        // Reference to instance methods of particular objects
        ComparisonProvider comp = new ComparisonProvider();
        System.out.println("Sorting students by name: ");
        students.sort(ComparisonProvider::compareByName);
        students.forEach(System.out::println);
        System.out.println("Sorting students by average: ");
        students.sort(comp::compareByAverage);
        students.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------");

        // Reference to an instance method of an arbitrary object of a particular type
        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda"};
        List<String> names = Arrays.asList(stringArray);
        System.out.println("Sorted list of names:");
        names.sort(String::compareToIgnoreCase);
        names.forEach(System.out::println);
        System.out.println("List of names converted to uppercase:");
        Capitalize cap = String::toUpperCase;
        List<String> capitalizedNames = new ArrayList<>();
        for (String name : names)
            capitalizedNames.add(cap.capitalize(name));
        capitalizedNames.forEach(System.out::println);
        System.out.println("Names not ending in 'A':");
        capitalizedNames.removeIf(x -> x.endsWith("A"));
        capitalizedNames.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------");

        // Reference to a constructor
        StudentFactory<Student> studentFactory = Student::new;
        Student newStudent = studentFactory.create(2, "Alina", 9.5);
        System.out.println("Newly created student: " + newStudent);
    }
}
