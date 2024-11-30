package repository;

public class DuplicateEntityException extends RepositoryException {
    DuplicateEntityException(String message){
        super(message);
    }
}
