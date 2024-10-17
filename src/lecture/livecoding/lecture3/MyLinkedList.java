package lecture.livecoding.lecture3;

import java.util.Iterator;

public class MyLinkedList<T> extends Object implements IList<T>, Iterable<T> {

    // referintele se initializeaza implicit cu valoarea null
    // tipurile numerice primitive se initializeaza implicit cu valoarea 0
    // tipul boolean se initializeaza implicit cu valoarea false
    private Node<T> head;


    @Override
    public void add(T e) {
        if (head == null) {
            head = new Node<T>(null, e);
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node<T>(null, e));
        }
    }

    @Override
    public T get(int index) {
        Node<T> current = head;
        while (index > 0) {
            current = current.getNext();
            index -= 1;
        }
        return current.getValue();
    }

    @Override
    public String toString() {
        // String is immutable is Java
        StringBuilder sb = new StringBuilder(); // implements the Builder design pattern
        sb.append(getClass().getName());
        sb.append("\r\n");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getValue());
            sb.append("\r\n");
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(head);
    }

    static class MyIterator<E> implements Iterator<E> {

        private Node<E> head;

        public MyIterator(Node<E> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head.getNext() != null;
        }

        @Override
        public E next() {
            E value = head.getValue();
            head = head.getNext();
            return value;
        }
    }

    static class Node<E> {

        private Node<E> next;
        private final E value; // final values cannot be reassigned to

        public Node(Node<E> next, E value) {
            this.next = next;
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
