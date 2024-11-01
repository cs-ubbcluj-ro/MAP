package lecture.livecoding;

import lecture.livecoding.domain.Shape2D;
import lecture.livecoding.repository.AbstractRepository;
import lecture.livecoding.repository.BinaryFileRepository;
import lecture.livecoding.repository.RepositoryException;

import java.io.IOException;

public class Lecture5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, RepositoryException {
        // clasa anonima derivata din clasa Shape
        // masina virtuala denumeste clasa anonima in Lecture5$1
//        Shape shape = new Shape(100) {
//            @Override
//            public float getArea() {
//                return 0;
//            }
//        };

//        Shape shape = new Rectangle(100, "My rectangle", 5, 3);

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.bin"));
//        oos.writeObject(shape);
//        oos.close();

//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.bin"));
//        Rectangle o = (Rectangle) ois.readObject();
//        ois.close();
//        System.out.println(o);

        AbstractRepository<Shape2D> repo = new BinaryFileRepository<>("repo_data.bin");
//        repo.add(new Rectangle(100, "one", 5, 4));
//        repo.add(new Rectangle(101, "two", 5, 4));

        for (var x : repo) {
            System.out.println(x);
        }


    }
}
