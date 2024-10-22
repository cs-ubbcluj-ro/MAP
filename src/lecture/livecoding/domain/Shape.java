package lecture.livecoding.domain;

public abstract class Shape {
    // NOTE java.lang.Object is the root of the Java inheritance hierarchy
    private int id; // setting to private does not allow subclasses to change this value
    protected String name;

    public Shape(int id) {
        this.id = id;
    }

    public Shape(int id, String name) {
        // A constructor can call another constructor
        // Constructor calls must be the first instruction
        this(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract float getArea(); // we can't determine the area of a shape without knowing more about it

    @Override
    public boolean equals(Object o) {
        // NOTE Automatically generated using IntelliJ
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape shape = (Shape) o;

        if (id != shape.id) return false;
        return name.equals(shape.name);
    }

    @Override
    public int hashCode() {
        // NOTE Automatically generated using IntelliJ
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
