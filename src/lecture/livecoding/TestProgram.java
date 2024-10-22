package lecture.livecoding;

import lecture.livecoding.domain.Rectangle;
import lecture.livecoding.domain.Square;
import lecture.livecoding.domain.UnitSquare;

public class TestProgram {
    public static void main(String[] args) {


        Rectangle r1 = new Rectangle(100, "the one", 10, 3);
        System.out.println(r1);

        Square s1 = new Square(101, "back to square one", 3);
        System.out.println(s1);

        UnitSquare unitSquare_1 = UnitSquare.getInstance();
        UnitSquare unitSquare_2 = UnitSquare.getInstance();

        // We use == comparison here in order to show that the two objects are the actual same instance
        System.out.println(unitSquare_1 == unitSquare_2);
    }
}
