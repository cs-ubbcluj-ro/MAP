package Repository;

public class NonExistEntity extends RepositoryException {
    public NonExistEntity(String message) {
        super(message);
    }
}
