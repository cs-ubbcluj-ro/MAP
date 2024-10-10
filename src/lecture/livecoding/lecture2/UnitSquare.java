package lecture.livecoding.lecture2;

public class UnitSquare extends Square {

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
