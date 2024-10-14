package lecture.examples.lecture3.generics;

class LinkedList<T> {
    private Node<T> first;

    LinkedList() {
        this.first = null;
    }

    public void add(T obj) {
        Node n = new Node(obj, null);

        if (this.first == null)
            this.first = n;
        else {
            n.setNext(this.first);
            this.first = n;
        }
    }

    ListIterator<T> iterator() {
        return new ListIterator();
    }

    private static class Node<T> {
        private final T data;
        private Node<T> next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        T getData() {
            return data;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }

    class ListIterator<T> {
        private Node current;

        ListIterator() {
            this.current = first;
        }

        boolean valid() {
            return this.current != null;
        }

        T element() {
            return (T) this.current.getData();
        }

        void next() {
            this.current = this.current.getNext();
        }
    }
}

public class GenericLinkedList {
    public static void main(String[] args) {
        LinkedList<String> l = new LinkedList<>();

        l.add("one");
        l.add("two");
        l.add("three");

        // LinkedList.ListIterator iterator = l.new ListIterator();
        LinkedList<String>.ListIterator<String> iterator = l.iterator();
        while (iterator.valid()) {
            String elem = iterator.element();
            System.out.println(elem);
            iterator.next();
        }
    }
}
