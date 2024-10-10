package lecture.livecoding.lecture2;

public class Square extends Rectangle {


    /**
     * Constructor for class Square. A square is a rectangle (as per the inheritance relation) where width == height
     *
     * @param id
     * @param name
     * @param width The width of the square.
     */
    public Square(int id, String name, int width) {
        super(id, name, width, width);
    }

    /**
     * Set the width AND height of the square. This method must override the one from the superclass to make sure the
     * Square has the same width and height
     *
     * @param width The updated width. This will also set the height to the same value
     */
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    /**
     * Set the width AND height of the square. This method must override the one from the superclass to make sure the
     * Square has the same width and height
     *
     * @param height The updated height. This will also set the width to the same value
     */
    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }

    @Override
    public String toString() {
        return "Square{ id=" + getId() +
                ", width=" + width +
                ", name='" + name + '\'' +
                " }";
    }
}
