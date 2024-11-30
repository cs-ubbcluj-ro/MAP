package domain;

public interface IEntityFactory<T extends Entity> {
    T createEntity(String line);
}
