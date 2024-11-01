package seminar.group321.seminar3.repository;

public class DuplicateEntityException extends Exception {
    DuplicateEntityException(String message) {
        super(message);
    }
}
