package lecture.livecoding.domain;

public abstract class ShapeConverter<T extends Shape2D> {
    public abstract String toString(T shape);

    public abstract T fromString(String string);
}
