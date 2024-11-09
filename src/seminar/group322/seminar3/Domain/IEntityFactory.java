package seminar.group322.seminar3.Domain;

public interface IEntityFactory<T extends Entity> {
    T createEntity(String line);
}
