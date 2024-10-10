package lecture.livecoding.lecture2;

public class Rectangle extends Shape {

    protected int width;
    protected int height;


    public Rectangle(int id, String name, int width, int height) {
        super(id, name); // call the base class constructor
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
