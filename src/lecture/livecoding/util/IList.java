package lecture.livecoding.util;

public interface IList<T> {
    void add(T e);

    T get(int index);

    void delete(T elem);

    default int size() {
        // metode implicite, posibile din Java 8
        return -1;
    }
}
