package lecture.livecoding.lecture3;

import lecture.livecoding.lecture2.Rectangle;
import lecture.livecoding.lecture2.UnitSquare;

import java.util.Iterator;

public class MainProgram {

    public static void main(String[] args) {
        MyLinkedList<Rectangle> myList = new MyLinkedList<>();

//        myList.add(new Object());
//        myList.add("1234");
//        myList.add(1234);

        int x = 2048;
//        myList.add(x);
        // Deprecated, should not use
//        myList.add(new Integer(2048));

        // Integer.valueOf is a Factory method (Factory Method design pattern)
//        myList.add(Integer.valueOf(2048));

//        myList.add(7890);
        myList.add(new Rectangle(100, "rectangle 1", 5, 4));
        myList.add(UnitSquare.getInstance());
        myList.add(UnitSquare.getInstance());
        myList.add(new Rectangle(100, "rectangle 1", 5, 4));
        myList.add(UnitSquare.getInstance());

        Iterator<Rectangle> iter = myList.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }

        for (Rectangle r : myList) {
            System.out.println(r);
        }


//        System.out.println(myList);
//        Rectangle val = myList.get(1);
//
//        System.out.println(val);
    }
}
