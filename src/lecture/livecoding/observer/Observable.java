package lecture.livecoding.observer;

public interface Observable {
    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);
}
