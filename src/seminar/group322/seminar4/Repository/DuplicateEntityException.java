package seminar.group322.seminar4.Repository;

public class DuplicateEntityException extends RepositoryException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
