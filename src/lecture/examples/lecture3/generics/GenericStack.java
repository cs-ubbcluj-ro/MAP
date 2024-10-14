package lecture.examples.lecture3.generics;

class Stack<E> {
    private int size = 0;

    // private E[] elems = new E[100]; // error - the type cannot be instantiated directly; all we know about it is that it is some subclass of Object

    // example of solution:
    private final E[] elems = (E[]) new Object[100];

    // static E max_dim; // not allowed because this variable belongs to the class; the class can be instantiated with different types

    void push(E elem) {
        if (this.size < this.elems.length)
            this.elems[this.size++] = elem;
    }

    E top() {
        if (this.size > 0)
            return this.elems[this.size - 1];
        else
            return null;
    }

    E pop() {
        if (size > 0) {
            E elem = elems[size - 1];
            size--;
            return elem;
        } else
            throw new RuntimeException("Stack is empty! Nothing to pop.");
    }
}

public class GenericStack {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(1); // autoboxing: automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes
        s.push(2);
        s.push(3);
        System.out.println("Top: " + s.top());
        s.pop(); // handle exception!
        System.out.println("Top: " + s.top());
    }
}
