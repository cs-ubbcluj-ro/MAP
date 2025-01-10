package lecture.examples.lecture12;

import java.io.Serializable;
import java.security.InvalidParameterException;

class Person
{
    private int id;
    private String name;

    public Person()
    {
        this.id = 1;
        this.name = "";
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Student extends Person implements Comparable, Serializable, WritableToCSV
{
    private double average;

    public Student(int id, String name, double average) {
        super(id, name);
        this.average = average;
    }

    /**
     * Constructor receives a string with the attributes separated by comma.
     * @param attributes: String (e.g. "1,Popescu Andrei,9.2")
     */
    public Student(String attributes)
    {
        String [] tokens = attributes.split("[,]");
        if (tokens.length < 3)
            throw new InvalidParameterException("The string is not correct!");
        int id = Integer.parseInt(tokens[0].strip());
        this.setId(id);
        String name = tokens[1].strip();
        this.setName(name);
        this.average = Double.parseDouble(tokens[2].strip());
    }

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return "Student{" +
                "average=" + average +
                "} " + super.toString();
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student)o;
        if (this.average < s.average)
            return -1;
        else if (this.average == s.average)
            return 0;
        else
            return 1;
    }

    @Override
    public String toCSV()
    {
        return this.getId() + ", " + this.getName() + ", " + this.getAverage();
    }
}
