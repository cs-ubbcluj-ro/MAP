package Domain;


import java.io.Serializable;

//nu putem crea obiecte de tip Entity, clasa e abstracta
public abstract class Entity implements Serializable {
    protected int id;
    public Entity(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
