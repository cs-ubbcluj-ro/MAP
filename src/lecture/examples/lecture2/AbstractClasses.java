package lecture.examples.lecture2;

abstract class AbstractAnimal {
    protected String colour;
    protected double weight;

    AbstractAnimal(String col, double w) {
        this.colour = col;
        this.weight = w;
    }

    public abstract void speak();
}

class Dog extends AbstractAnimal {
    Dog(String col, double w) {
        super(col, w);
    }

    public void speak() {
        System.out.println("Woof woof");
    }
}

public class AbstractClasses {
    public static void main(String[] args) {
//        AbstractAnimal animal = new AbstractAnimal(); // ERROR
        AbstractAnimal dog = new Dog("black", 24);
        dog.speak();

        AbstractAnimal cat = new AbstractAnimal("white", 4) {
            @Override
            public void speak() {
                System.out.println("Meow");
            }
        };
        cat.speak();
    }
}