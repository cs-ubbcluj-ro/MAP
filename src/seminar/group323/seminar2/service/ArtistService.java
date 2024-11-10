package seminar.group323.seminar2.service;

import seminar.group323.seminar2.domain.Artist;
import seminar.group323.seminar2.repository.IRepository;
import seminar.group323.seminar2.repository.RepositoryException;

public class ArtistService {
    IRepository<Artist> artistRepo;

    public ArtistService(IRepository<Artist> artistRepo) {
        // NOTE Injectam dependenta la repository prin constructor
        this.artistRepo = artistRepo;
    }

    public void addArtist(int id, String name, String genre) throws RepositoryException {
        Artist art = new Artist(id, name, genre);
        // TODO Validari!?
        artistRepo.add(art);
    }

}
