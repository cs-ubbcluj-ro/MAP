package seminar.group322.seminar3.Repository;

public class DuplicateEntityException extends RepositoryException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
