package repository;

public class EntityNotFoundException extends RepositoryException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
