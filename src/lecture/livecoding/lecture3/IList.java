package lecture.livecoding.lecture3;

public interface IList<T> {
    void add(T e);

    T get(int index);

    default int size() {
        // metode implicite, posibile din Java 8
        return -1;
    }
}
