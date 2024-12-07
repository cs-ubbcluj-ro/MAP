package Repository;

public class DuplicateEntityException extends RepositoryException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
