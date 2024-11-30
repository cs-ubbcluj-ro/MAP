package service;

import domain.Musician;
import repository.IRepository;
import repository.RepositoryException;

import java.util.Collection;

public class MusicianService {
    IRepository<Musician> repoMusician;

    public MusicianService(IRepository<Musician> repository){
        repoMusician = repository;
    }

    public void add(int id, String name, int age, String genre) throws RepositoryException {
        Musician m = new Musician(id, name, age, genre);
        //validare musician
        repoMusician.add(m);
    }

    public Collection<Musician> getAll(){
        return repoMusician.getAll();
    }

    public void remove(int id) throws RepositoryException {
        repoMusician.remove(id);
    }


}
