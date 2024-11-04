package lecture.examples.lecture6;

public class Student {
    private final int id;
    private String name;
    private final double average;

    public Student(int id, String name, double average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverage() {
        return average;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", average=" + average +
                '}';
    }
}

class ComparisonProvider {
    public static int compareByName(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }

    public int compareByAverage(Student s1, Student s2) {
        if (s1.getAverage() < s2.getAverage())
            return -1;
        else if (s1.getAverage() > s2.getAverage())
            return 1;
        else
            return 0;
    }
}
