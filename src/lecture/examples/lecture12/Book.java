package lecture.examples.lecture12;

import java.security.InvalidParameterException;

class Book implements WritableToCSV {
    private String authors;
    private String title;
    private int numberOfPages;

    public Book(String authors, String title, int numberOfPages) {
        this.authors = authors;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    /**
     * Constructor receives a string with the attributes separated by comma.
     *
     * @param attributes: String (e.g. "Tom Mitchell; Jean Barto,Machine learning,530")
     */
    public Book(String attributes) {
        String[] tokens = attributes.split("[,]");
        if (tokens.length < 3)
            throw new InvalidParameterException("The string is not correct!");
        this.authors = tokens[0].strip();
        this.title = tokens[1].strip();
        this.numberOfPages = Integer.parseInt(tokens[2].strip());
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors='" + authors + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    public String getAuthors() {
        return authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toCSV() {
        return this.authors + ", " + this.title + ", " + this.numberOfPages;
    }
}
