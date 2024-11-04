package seminar.group321.seminar3.domain;

//clasa abstracta: nu putem crea obiecte de tip Entity

import java.io.Serializable;

public abstract class Entity {
    protected int id;
    Entity(int id) {
        this.id = id;
    }
//    Entity(){
//        System.out.println("Constructor fara parametri");
//    }

    public int getId() {
        return id;
    }

}