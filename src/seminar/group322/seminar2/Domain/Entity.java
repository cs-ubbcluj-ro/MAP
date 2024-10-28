package seminar.group322.seminar2.Domain;


//nu putem crea obiecte de tip Entity, clasa e abstracta
public abstract class Entity {
    protected int id;
    public Entity(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
