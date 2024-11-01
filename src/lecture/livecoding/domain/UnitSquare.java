package lecture.livecoding.domain;

/**
 * This class provides an early example of the Singleton design pattern. This implementation is not thread-safe! This
 * means that it is correct only as long as the program is run on a single thread.
 */
public class UnitSquare extends Square {

    // This variable holds the unique, shared instance of UnitSquare. Remember that since UnitSquare is derived from
    // class Square, a Unit Square is a Square
    private static UnitSquare instance;

    /**
     * The UnitSquare has an id of 1, a constant name and a width of 1.
     * NB! The private constructor is used to implement the Singleton design pattern
     * <p>
     * This implementation is NOT thread-safe
     */
    private UnitSquare() {
        super(1, "The Unit Square", 1);
    }

    public static UnitSquare getInstance() {
        if (UnitSquare.instance == null) {
            // Lazy loading so that we only create the object when we know it is required
            UnitSquare.instance = new UnitSquare();
        }
        return instance;
    }
}
