package seminar.group321.seminar2;

import seminar.group321.seminar2.domain.Manager;
import seminar.group321.seminar2.domain.Musician;
import seminar.group321.seminar2.domain.Persoana;
import seminar.group321.seminar2.repository.Repository;

import java.util.ArrayList;

public class Main {
    /*
   1. Clasa abstracta Entity, camp ID de tip int -done
       - ID este camp protected
       - ID-ul se poate seta prin constructorul clasei
       - metoda publica prin care se poate accesa ID: getID()

   2. Clasa Persoana care este derivata din Entity -done
       - campuri: name (String), age (int)

   3. Clasa Musician derivata din Persoana - done
       - campuri: genre (String)

   4. Clasa Manager derivata din Persoana: - done
       -campuri: affiliation (String), managedMusicians (ArrayList<Musician>)

   5. Clasa repository.Repository<T> generica (T va fi fie Musician, fie Manager) - done
       -camp: ArrayList<T> in care se stocheaza entitati din domeniu
       -posibiltate de:
           --adaugare entitate - done
           --cautare entitate dupa ID - done
           --stergere entitate - done
       -make it nice, make it implement the Iterable interface - not done, adaugat comentarii cu privire la modificarile
           care ar fi necesare in fisierul cu clasa Repository
    */


    public static void main(String[] args) {

        //arraylist de referinte tip Persoana
        //poate contine obiecte Persoana, Musician, Manager
        ArrayList<Persoana> persoane = new ArrayList<>();

        //Choice: ar trebui sa putem crea obiecte de tip Persoana? Sau doar Musician si Manager?
        Persoana p1 = new Persoana(1, "nume1", 18);
        Persoana p2 = new Persoana(2, "nume2", 20);
        Persoana p3 = new Persoana(3, "nume3", 67);
        Musician m1 = new Musician("nume4", 65, 4, "horoor");
        Manager mg1 = new Manager(10, "manager", 53, "MGM");

        persoane.add(p1);
        persoane.add(p2);
        persoane.add(p3);
        persoane.add(m1);
        persoane.add(mg1);

        System.out.println("Continut ArrayList de persoane:");
        for (Persoana p : persoane) {
            System.out.println(p);
            //referinta e tip Persoana
            //play e o metoda care exista doar in clasa Musician
            //desi noi stim ca avem si Musician in ArrayList,
            //nu putem accesa decat metodele din Persoana
            //compilatorul verifica clasa referintei, nu a obiectului
            //efectiv la care arata referinta, deci "vede" doar metodele
            //din clasa Persoana

            //this doesn't work
            //p.play()

            //can do something like this if necessary
            if (p instanceof Musician) {
                Musician m = (Musician) p;
                m.play();
            }
        }

        //partea de mai sus se poate comenta, inclusa doar pentru exemplificare
        System.out.println("----------------------------");

        System.out.println("De aici afisam din repo:");
        Repository<Musician> repo = new Repository<>();

        Musician m5 = new Musician("nume4", 65, 4, "horoor");

        try {
            repo.add(m1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Putem decomenta codul acesta daca facem Repo Iterable (vezi comentarii in clasa Repository)
//        for (Musician m: repo) {
//            System.out.println(m);
//        }


        System.out.println("Inainte de remove exista " + repo.getSize() + " elemente in repository.");
        try {
            repo.remove(m1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Dupa remove exista " + repo.getSize() + " elemente in repository.");

//        try {
//            repo.removeById(4);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("Dupa remove exista " + repo.getSize() + " elemente in repository.");

    }
}