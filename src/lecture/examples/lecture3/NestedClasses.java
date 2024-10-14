package lecture.examples.lecture3;

class LinkedList {
    private Node first;

    LinkedList() {
        this.first = null;
    }

    void add(Object obj) {
        Node n = new Node(obj, null);

        if (this.first == null)
            this.first = n;
        else {
            n.setNext(this.first);
            this.first = n;
        }
    }

    ListIterator iterator() {
        return new ListIterator();
    }

    private class Node {
        private final Object data;
        private Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        Object getData() {
            return data;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }

    class ListIterator {
        private Node current;

        ListIterator() {
            this.current = first;
        }

        boolean valid() {
            return this.current != null;
        }

        Object element() {
            return this.current.getData();
        }

        void next() {
            this.current = this.current.getNext();
        }
    }
}

public class NestedClasses {

    public static void main(String[] args) {

        String e1 = "one";
        String e2 = "two";
        String e3 = "three";

        LinkedList l = new LinkedList();
        l.add(e1);
        l.add(e2);
        l.add(e3);

        LinkedList.ListIterator iterator = l.new ListIterator();
//        LinkedList.ListIterator iterator = l.iterator();
        while (iterator.valid()) {
            String elem = (String) iterator.element();
            System.out.println(elem);
            iterator.next();
        }
    }
}

