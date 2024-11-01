package seminar.group321.seminar3.domain;

public interface IEntityFactory<T extends Entity> {
    T createEntity(String line);
}
