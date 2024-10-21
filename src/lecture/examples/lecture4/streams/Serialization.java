package lecture.examples.lecture4.streams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable {
    public static final long serialVersionUID = 2L;

    private String name;
    private double average;
    private transient String password;

    Student(String name, double average, String pass) {
        this.name = name;
        this.average = average;
        this.password = pass;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", average=" + average + ", password='" + password + '\'' + '}';
    }
}

public class Serialization {
    static void serializeStudentList(List<Student> students, String filename) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(students);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static List<Student> deserializeStudentList(String filename) {
        ObjectInputStream in = null;
        List<Student> list = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            list = (List<Student>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Cosmin", 8.8, "aaaa"));
        students.add(new Student("Alina", 9.7, "bbbb"));
        serializeStudentList(students, "src/lecture/examples/lecture4/streams/students.bin");

//        List<Student> deserializedStudents = deserializeStudentList("src/streams/students.bin");
//        for (Student s: deserializedStudents)
//            System.out.println(s);
    }
}
