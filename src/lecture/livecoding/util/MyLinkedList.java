package lecture.livecoding.util;

import java.util.Iterator;

public class MyLinkedList<E> implements IList<E>, Iterable<E> {

    // referintele se initializeaza implicit cu valoarea null
    // tipurile numerice primitive se initializeaza implicit cu valoarea 0
    // tipul boolean se initializeaza implicit cu valoarea false
    private Node<E> head;

    private int size;

    @Override
    public void add(E e) {
        if (head == null) {
            head = new Node<E>(null, e);
        } else {
            Node<E> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node<E>(null, e));
        }
        size += 1;
    }

    @Override
    public void delete(E elem) {
        // TODO De implementat
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        Node<E> current = head;
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
        sb.append(getClass().getName()).append(" of size ").append(size()).append("\r\n");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getValue());
            sb.append("\r\n");
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
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
