package lecture.examples.lecture3;

import java.util.Iterator;
import java.util.LinkedList;

class MyVector implements Iterable<Integer> {
    private final Integer[] elems = new Integer[10];
    private int size = 0;

    public void add(Integer e) {
        this.elems[this.size++] = e;
    }

    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<Integer> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < size;
        }

        @Override
        public Integer next() {
            Integer i = elems[this.current];
            this.current++;
            return i;
        }
    }
}

public class Iterators {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(20);
        list.add(-3);
        list.add(44);

        // for each
        for (Integer i : list)
            System.out.println(i);
        System.out.println("------------------------------------------------");

        // using iterators
        Iterator<Integer> it = list.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        System.out.println("------------------------------------------------");

        MyVector myVect = new MyVector();
        myVect.add(10);
        myVect.add(20);
        myVect.add(30);
        myVect.add(40);

        // to allow this, the class MyVector must implement the Iterable interface
        for (Integer e : myVect)
            System.out.println(e);

        Iterator it1 = myVect.iterator();
        while (it1.hasNext())
            System.out.println(it1.next());
    }
}
