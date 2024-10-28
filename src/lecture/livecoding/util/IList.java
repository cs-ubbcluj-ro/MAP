package lecture.livecoding.util;

public interface IList<E> {
    void add(E e);

    E get(int index);

    void delete(E elem);

    int size();
}
