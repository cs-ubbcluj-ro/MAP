package lecture.examples.lecture12;

import java.lang.reflect.*;

public class Reflection {
    public static void main(String[] args) {
        // getting the class using the class name (if this is known)
        Class studentClass1 = Student.class;
        System.out.println("Class: " + studentClass1);

        // getting the class via an object of the class
        Student s = new Student(2, "Pop Andrei", 9.2);
        Class studentClass2 = s.getClass();
        System.out.println("Class: " + studentClass2);
        // getting the class name, if this is not known
        System.out.println("Class: " + studentClass2.getName());

        try {
            // getting a class by its fully-qualified name
            Class studentClass3 = Class.forName("reflection.Student");
            System.out.println("Class by name: " + studentClass2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // getting the package
        Package p = studentClass2.getPackage();
        System.out.println("Package for student class: " + p.getName());

        // getting the superclass
        Class superClass = studentClass2.getSuperclass();
        System.out.println("Superclass of student class: " + superClass.getName());

        // getting the interfaces
        Class [] interfaces = studentClass2.getInterfaces();
        System.out.println("Interfaces implemented by the student class: ");
        for (Class i: interfaces)
            System.out.println("\t" + i);

        // getting constructors
        Constructor[] constructors = studentClass2.getConstructors();
        for (Constructor c: constructors) {
            System.out.println("Constructor: " + c);
            System.out.println("Parameters: ");
            Class[] parameterTypes = c.getParameterTypes();
            for (Class type: parameterTypes)
                System.out.println("\t" + type);
        }

        try {
            Constructor constructor = studentClass2.getConstructor(String.class);
            System.out.println("Constructor with parameter String: " + constructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            Constructor constructor = studentClass2.getConstructor(int.class, String.class, double.class);
            System.out.println("Constructor with 3 parameters (int, String, double): " + constructor);

            // construct an object with the retrieved constructor
            Student newObject = (Student) constructor.newInstance(2, "Marin Ioana", 9.4);
            System.out.println("Object created with newInstance: " + newObject);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // working with fields
        Field[] fields = studentClass2.getDeclaredFields();
        System.out.println("Private fields for class Student: ");
        for (Field f: fields)
            System.out.println("\t" + f);
        try {
            Field f = studentClass2.getDeclaredField("average");
            // make the private field accessible
            f.setAccessible(true);
            Student st = new Student(2, "Marin Ioana", 9.4);
            Object value = f.get(st);
            System.out.println("Value for field 'average': " + value);
            f.set(st, 10);
            System.out.println("Value for field 'average': " + st.getAverage());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // working with methods
        Method[] methods = studentClass2.getMethods();
        System.out.println("Public methods of class Student: ");
        for (Method m: methods)
            System.out.println("\t" + m);

        try {
            Method m = studentClass2.getMethod("compareTo", Object.class);
            System.out.println("Method by name (compareTo): " + m);

            Class[] parameterTypes = m.getParameterTypes();
            System.out.println("Parameter types for method 'compareTo': ");
            for (Class pt: parameterTypes)
                System.out.println("\t" + pt);
            Class returnType = m.getReturnType();
            System.out.println("Return type for method 'compareTo': ");
            System.out.println("\t" + returnType);

            // invoking the method
            Student st1 = new Student(2, "Marin Ioana", 8.4);
            Student st2 = new Student(3, "Bojan Paul", 9.1);
            System.out.println("Result of comparing 2 students by invoking the method: " + m.invoke(st1, st2));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
