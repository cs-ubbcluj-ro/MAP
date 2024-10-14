package lecture.examples.lecture3;

import java.util.Arrays;

interface MyComparable<E> {
    int compareTo(E element);
}

class Calculator<T extends Number> {
    private final T[] numbers;

    Calculator(T[] numbers) {
        this.numbers = numbers;
    }

    double average() {
        double sum = 0.0;
        for (int i = 0; i < this.numbers.length; i++) {
            sum += this.numbers[i].doubleValue();
        }
        return sum / this.numbers.length;
    }

    public double add(T number1, T number2) {
        return number1.doubleValue() + number2.doubleValue();
    }
}

class Vector<E extends MyComparable<E>> {
    private final E[] elems;

    Vector(E[] elems) {
        this.elems = elems;
    }

    void sort() {
        boolean swapped = true;
        int j = 0;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < this.elems.length - j; i++) {
                if (this.elems[i].compareTo(this.elems[i + 1]) > 0) {
                    E aux = this.elems[i];
                    this.elems[i] = this.elems[i + 1];
                    this.elems[i + 1] = aux;
                    swapped = true;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Vector{" +
                "elems=" + Arrays.toString(elems) +
                '}';
    }
}


public class BoundedTypeParameters {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5};
        Calculator<Integer> calc1 = new Calculator<>(integers);
        System.out.println("Average for integers is: " + calc1.average());
        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Calculator<Double> calc2 = new Calculator<>(doubles);
        double avg2 = calc2.average();
        System.out.println("Average for integers is: " + calc2.average());
        System.out.println("Sum: " + calc2.add(2.5, 3.3));

        Person[] persons = {new Person("Vlad"), new Person("Bianca"), new Person("Gabriela")};
        Vector<Person> v1 = new Vector<>(persons);
        v1.sort();
        System.out.println(v1);
    }
}
