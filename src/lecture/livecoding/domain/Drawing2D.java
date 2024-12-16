package lecture.livecoding.domain;

import java.util.ArrayList;
import java.util.List;

public class Drawing2D extends Shape2D {

    private final List<Shape2D> children = new ArrayList<>();

    public Drawing2D(int id, String name) {
        super(id, name);
    }

    public void addChild(Shape2D shape) {
        // TODO De verificat existenta unui ciclu infinit
        children.add(shape);
    }

    public void removeChild(Shape2D shape) {
        children.remove(shape);
    }

    @Override
    public float getArea() {
        float total = 0;
        for (var shape : children) {
            total += shape.getArea();
        }
        return total;
    }
}
