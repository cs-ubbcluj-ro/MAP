package lecture.livecoding.domain;

import java.io.Serializable;

/**
 * Represents a 2D shape. This is the root of our program domain's inheritance tree. Like all classes for which a base
 * class is not explicitly defined, class Shape2D is derived from java.lang.Object
 * <p>
 * We mark class Shape2D as abstract because we know that a 2D shape has an area, but we generally don't know how to
 * calculate it. This however allows us to defined the getArea() method, which is implemented by its subclasses.
 * <p>
 * Shape2D also implements java.io.Serializable, so we can use an ObjectOutputStream object to transform it into an array
 * of bytes and save it to a file. Since the root class implemented Serializable, it means all classes that are derived
 * from Shape2D also implement this marker interface (marker = there is no method to implement)
 */
public abstract class Shape2D implements Serializable {

    // Setting to private does not allow subclasses to change this value
    private int id;
    protected String name;

    public Shape2D(int id) {
        this.id = id;
    }

    public Shape2D(int id, String name) {
        // A constructor can call another constructor, but this call must be the first instrcution in the method
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

    // We can't determine the area of a shape without knowing more about it
    public abstract float getArea();

    @Override
    public boolean equals(Object o) {
        // NOTE Automatically generated using IntelliJ
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape2D shape = (Shape2D) o;

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
