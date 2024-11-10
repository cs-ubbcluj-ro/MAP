package lecture.livecoding.domain;

public class SquareConverter extends ShapeConverter<Square> {
    @Override
    public String toString(Square shape) {
        return shape.getId() + ";" + shape.getName() + ";" + shape.getWidth();
    }

    @Override
    public Square fromString(String string) {
        String[] tokens = string.split(";");
        // TODO Handle exceptions
        int id = Integer.parseInt(tokens[0]);
        String name = tokens[1];
        int width = Integer.parseInt(tokens[2]);
        return new Square(id, name, width);
    }
}
