package lecture.livecoding;

import lecture.livecoding.domain.Drawing2D;
import lecture.livecoding.domain.Rectangle;
import lecture.livecoding.domain.Square;
import lecture.livecoding.domain.UnitSquare;
import lecture.livecoding.observer.Observer;
import lecture.livecoding.repository.MemoryRepository;
import lecture.livecoding.repository.RepositoryException;

public class Lecture10 {
    public static void main(String[] args) throws RepositoryException {

        /**
         * Exemplu pentru sablonul de proiectare Composite
         * https://refactoring.guru/design-patterns/composite
         */
        Drawing2D drawing = new Drawing2D(1, "my drawing");
        drawing.addChild(UnitSquare.getInstance());
        drawing.addChild(new Rectangle(100, "rectangle 1", 5, 4));

        Drawing2D child_drawing = new Drawing2D(2, "child drawing");
        child_drawing.addChild(new Square(101, "square 1", 2));
        child_drawing.addChild(new Square(102, "square 2", 2));

        drawing.addChild(child_drawing);
//        drawing.addChild(drawing); // !!??

//        System.out.println(drawing.getArea());

        /**
         * Exemplu pentru sablonul de proiectare Observer
         * https://refactoring.guru/design-patterns/observer
         */
        MemoryRepository<Square> repo = new MemoryRepository<>();
        repo.registerObserver(() -> System.out.println("observer 1"));

        var anotherObserver = new Observer() {
            @Override
            public void notifyObserver() {
                System.out.println("observer 2");
            }
        };

        repo.registerObserver(anotherObserver);
        repo.registerObserver(new Observer() {
            @Override
            public void notifyObserver() {
                System.out.println("observer 3");
            }
        });


        repo.add(UnitSquare.getInstance());
        repo.unregisterObserver(anotherObserver);
        repo.add(new Square(2, "A", 5));

    }
}
