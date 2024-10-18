package seminar.group321.seminar2.repository;

import seminar.group321.seminar2.domain.Entity;

import java.util.ArrayList;

/*
<T extends Entity> -> bounded type parameters (Curs 3)
    -restrangem tipurile de date ce pot fi utilizate la Entity si subclase ale Entity
    -si daca Entity ar fi fost o interfata, am fi scris tot Repository<T extends Entity>
        (dar am fi scris Persoana *implements* Entity)
    -putem apela metode din clasa Entity (e.g. getId())

 */

/*
TO DO: make Repo Iterable - daca facem asta, am putea folosi

Repo<Musician> repo = new Repo<>();
...add musicians...
for (Musician m: repo){
    ...do something with the Musician information...
}

Modificarea ar presupune:

public class Repository<T extends Entity> implements Iterable<T>

si adaugarea metodei
@Override
    public Iterator<T> iterator() {
        return entities.iterator();
    }

 -in aceasta varianta returnam iterator peste ArrayList
am putea sa implementam un iterator - stim cum de la SDA
(clasa RepoIterator care implementeaza interfata Iterator,
contine referinta la Repo (colectia peste care se itereaza) si un index - sound familiar?)
 */
public class Repository<T extends Entity> {
    ArrayList<T> entities;

    public Repository() {
        entities = new ArrayList<>();
    }

    boolean findByID(int id) {

        for (T entity : entities) {
            if (entity.getId() == id)
                return true;
        }
        return false;
    }

    public void add(T entity) throws Exception {
        if (findByID(entity.getId()))
            throw new Exception("Exista deja o entitate cu id-ul dat.");
        entities.add(entity);
    }

    //putem implementa stergerea si ca removeById(int id)
    public void removeById(int id) throws Exception {
        if (!findByID(id))
            throw new Exception("Nu exista entitatea cu id-ul dat");
        entities.remove(getEntityById(id));

    }

    private T getEntityById(int id) {
        for (T e : entities)
            if (e.getId() == id)
                return e;
        return null;
    }

    //care ar fi avantaje/dezavantaje la fiecare mod de a scrie remove?
    public void remove(T entity) throws Exception {
        if (!findByID(entity.getId()))
            throw new Exception("Nu exista entitatea cu id-ul dat");

        //din specificatia metodei remove:
        //Removes the element with the lowest index i such that Objects.equals(o, get(i))
        //(if such an element exists).
        // (!!) equals() intre obiectele noastre?
        entities.remove(entity);
    }

    public int getSize() {
        return entities.size();
    }


}
