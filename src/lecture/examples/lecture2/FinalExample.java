package lecture.examples.lecture2;

class Base {
    static final double PI = 3.14159;
    final int CAPACITY = 200;
    final int MINIMUM;

    {
        MINIMUM = 0;
    }

    final void fct() {
        System.out.println("Base class.");
    }
}

class Derived extends Base {
//    @Override
//    void fct() // ERROR
//    {
//        System.out.println("Derived class");
//    }
}

final class BaseFinal {
}

//class DerivedFromBaseFinal extends BaseFinal { // ERROR
//
//}

public class FinalExample {
    public static void main(String[] args) {
//         Base.PI = 3.14; // ERROR
    }
}
