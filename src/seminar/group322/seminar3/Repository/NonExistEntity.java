package seminar.group322.seminar3.Repository;

//ar fi bine sa punem "Exception" dupa NonExistEntity, ca sa stim ca este o exceptie
public class NonExistEntity extends RepositoryException {
    public NonExistEntity(String message) {
        super(message);
    }
}
