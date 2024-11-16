package seminar.group321.seminar4.repository;

public class DuplicateEntityException extends RepositoryException {
    DuplicateEntityException(String message){
        super(message);
    }
}
