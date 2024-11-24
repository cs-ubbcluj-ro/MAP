package seminar.group322.seminar4.Domain;

public interface IEntityFactory<T extends Entity> {
    T createEntity(String line);
}
