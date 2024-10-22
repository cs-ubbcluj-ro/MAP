package seminar.group323.seminar2.service;

import seminar.group323.seminar2.domain.Artist;
import seminar.group323.seminar2.repository.IRepository;

public class ArtistService {
    IRepository<Artist> artistRepo;

    public ArtistService(IRepository<Artist> artistRepo) {
        // NOTE Injectam dependenta la repository prin constructor
        this.artistRepo = artistRepo;
    }

}
