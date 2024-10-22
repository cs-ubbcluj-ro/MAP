package seminar.group323.seminar2.domain;

public class Artist extends Person {

    // referintele sunt initializate cu null
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Artist(int id, String name, String genre) {
        super(id, name);
        this.genre = genre;
    }
}
