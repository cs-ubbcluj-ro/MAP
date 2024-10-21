package lecture.examples.lecture4.exceptions;

class MyException1 extends Exception {
}

class MyException2 extends Exception {
}

class MyException3 extends MyException1 {
}

class IncorrectNumberOfArgumentsException extends IndexOutOfBoundsException {
}

class A {
    public A() throws MyException1 {
    }

    public void f() throws MyException1 {
    }
}

class B extends A {
    public B() throws MyException1, MyException2 {
    } // the derived class constructor must throw
    // MyException1 and can add new exceptions.

    @Override
    public void f() throws MyException1  // can only throw MyException1 or anything derived from it
    // cannot throw MyException2
    {
        super.f();
    }
}

public class Examples {
    public static void equation(int[] coefficients) // solve equation: ax + b = 0
    {
        if (coefficients.length < 2)
            throw new IncorrectNumberOfArgumentsException(); // unchecked exception, therefore no need to specify it

        if (coefficients[1] % coefficients[0] == 0)
            System.out.println("Solution: " + -coefficients[1] / coefficients[0]);
        else System.out.println("There is no integer solution.");
    }

    public static void function1() {
//        int [] coef = {0, 20};
        int[] coef = {20};
//        equation(coef);

        try {
            equation(coef);
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Make sure you input 2 coefficients!");
        }
//        catch (IncorrectNumberOfArgumentsException e)
//        {
//            System.out.println(e.getMessage());
//        }
        finally {
            System.out.println("Done! One way or another.");
        }
    }

    public static void main(String[] args) {
        function1();
    }
}
