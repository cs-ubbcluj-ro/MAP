package seminar.group321.seminar4.domain;

public class Musician extends Persoana {
    protected String genre;

    public Musician(int id, String name, int age, String genre) {
        super(id, name, age);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void play() {
        System.out.println("I am a Musician and I am playing " + genre + "...");
    }


    @Override
    public String toString() {
        //adaugat fata de seminar: sa nu se scrie dupa } care inchide string-ul returnat din
        //Persoana, inlocuim si concatenam informatia suplimentara in acelasi format
        return super.toString().replace("}", "") + ", genul=" + genre + "}";
    }
}
