package lecture.examples.lecture7;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExamples {
    public static void main(String[] args) {


//        StreamsExamples.main(new String[] {"a","2","3"});

        List<String> names =
                Arrays.asList("Barbara", "James",
                        "Brooke", "Emilia", "Boris");

        // map
        System.out.println("Map example: ");
        names.stream()
                .map(x -> x.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-------------------------------");

        // filter
        System.out.println("Filter example: ");
        names.stream()
                .filter(x -> x.endsWith("a"))
                .forEach(System.out::println);
        System.out.println("-------------------------------");

        // sorted
        System.out.println("Sorted example 1: ");
        names.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Sorted example 2: ");
        names.stream()
                .sorted((x, y) -> {

                    if (x.length() < y.length()) return -1;
                    else if (x.length() == y.length()) return 0;
                    else return 1;
                })
                .forEach(System.out::println);
        System.out.println("-------------------------------");

        // reduce
        System.out.println("Reduce example 1: ");
        Optional<String> op = names.stream()
                .reduce(String::concat);
        op.ifPresent(System.out::println);

        System.out.println("Reduce example 2: ");
        String concatenationWithDash = names.stream()
                .reduce("", (x, y) -> x + " - " + y);
        System.out.println(concatenationWithDash);

        System.out.println("Reduce example 3: ");
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum: " + sum);

        sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(0, (x, y) -> x + y);
        System.out.println("Sum: " + sum);

        int count = (int) Stream.of(1, 2, 3, 4, 5)
                .count();
        System.out.println("Count: " + count);
        System.out.println("-------------------------------");

        // match
        List<Student> students =
                Arrays.asList(new Student(1, "Mihai", 8.70),
                        new Student(2, "Ana", 8.70),
                        new Student(3, "Ionut", 9),
                        new Student(4, "Bianca", 5.5));
        boolean allPassed = students.stream()
                .allMatch(s -> s.getAverage() > 5);
        boolean anyGreaterThan9 = students.stream()
                .anyMatch(s -> s.getAverage() >= 9);
        boolean nonePassed = students.stream()
                .noneMatch(s -> s.getAverage() > 5);
        System.out.println("All students passed: " + allPassed);
        System.out.println("Any student has an average > 9: " + anyGreaterThan9);
        System.out.println("None of the students passed: " + nonePassed);
        System.out.println("-------------------------------");

        // processing order
        Stream.of("a", "b", "c", "d")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        System.out.println();
        // anyMatch will return true as soon as the predicate applies to the given input element
        // as such, map will be called as few times as possible
        Stream.of("b", "a1", "c", "a2", "d")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
        System.out.println();

        // map and filter are called 5 times and forEach just once
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println();

        // changing the order of the operations reduces the number of executions
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));


        Student s1 = new Student(1, "Mihai", 8.70);
        Student s2 = new Student(2, "Ana", 8.70);
        Student s3 = new Student(3, "Ionut", 9);
        Discipline d1 = new Discipline(1, "APM");
        Discipline d2 = new Discipline(2, "DB");
        StudentDiscipline sd1 = new StudentDiscipline(s1, d1);
        StudentDiscipline sd2 = new StudentDiscipline(s1, d2);
        StudentDiscipline sd3 = new StudentDiscipline(s2, d1);
        StudentDiscipline sd4 = new StudentDiscipline(s3, d2);

        // all students who study "APM", sorted alphabetically
        Stream.of(sd1, sd2, sd3, sd4)
                .filter(sd -> sd.getDiscipline().getName().equals("MAP"))
                .map(sd -> sd.getStudent())
                .sorted((x, y) -> x.getName().compareTo(y.getName()))
                .forEach(System.out::println);

        StudentDisciplineIds sdi1 =
                new StudentDisciplineIds(1, 1);
        StudentDisciplineIds sdi2 =
                new StudentDisciplineIds(1, 2);
        StudentDisciplineIds sdi3 =
                new StudentDisciplineIds(2, 1);
        StudentDisciplineIds sdi4 =
                new StudentDisciplineIds(3, 2);

        // all students who study "MAP", alphabetically
        Discipline discMAP = Stream.of(d1, d2)
                .filter(d -> d.getName().equals("APM"))
                .findAny()
                .orElse(null);
        int idMAP = -1;
        if (discMAP != null)
            idMAP = discMAP.getId();
        final int idMAPFinal = idMAP;

        List<Integer> studentsIds = Stream.of(sdi1, sdi2, sdi3, sdi4)
                .filter(sdi -> sdi.getDisciplineId() == idMAPFinal)
                .map(sd -> sd.getStudentId())
                .collect(Collectors.toList());

        Stream.of(s1, s2, s3)
                .filter(s -> studentsIds.contains(s.getId()))
                .sorted((st1, st2) -> s1.getName().compareTo(s2.getName()))
                .forEach(System.out::println);
    }
}
