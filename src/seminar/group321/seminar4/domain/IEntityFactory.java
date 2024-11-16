package seminar.group321.seminar4.domain;

public interface IEntityFactory<T extends Entity> {
    T createEntity(String line);
}
