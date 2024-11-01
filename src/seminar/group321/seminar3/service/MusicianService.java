package seminar.group321.seminar3.service;

import seminar.group321.seminar3.domain.Musician;
import seminar.group321.seminar3.repository.DuplicateEntityException;
import seminar.group321.seminar3.repository.IRepository;

import java.util.Collection;

public class MusicianService {
    IRepository<Musician> repoMusician;

    public MusicianService(IRepository<Musician> repository){
        repoMusician = repository;
    }

    public void add(int id, String name, int age, String genre) throws DuplicateEntityException {
        Musician m = new Musician(id, name, age, genre);
        //validare musician
        repoMusician.add(m);
    }

    public Collection<Musician> getAll(){
        return repoMusician.getAll();
    }



}
