package lecture.livecoding.domain;

import java.io.Serial;

/**
 * This class is derived from Shape2D. Since it implements the inherited abstract method getArea(), it is not longer
 * abstract and can be instantiated
 */
public class Rectangle extends Shape2D {

    // This is a special variable with a well defined meaning in Java serialization
    // Find out more at https://www.baeldung.com/java-serial-version-uid
    @Serial
    private static final long serialVersionUID = 1;

    protected int width;
    protected int height;


    public Rectangle(int id, String name, int width, int height) {
        // Calls the base class constructor
        super(id, name);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{ id=" + getId() +
                ", width=" + width +
                ", height=" + height +
                ", name='" + name + '\'' +
                " }";
    }

    @Override
    public float getArea() {
        return width * height;
    }
}
