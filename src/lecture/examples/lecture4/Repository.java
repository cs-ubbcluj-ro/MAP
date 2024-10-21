package lecture.examples.lecture4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Book {
    private final String authors;
    private final String title;
    private final int numberOfPages;

    public Book(String authors, String title, int numberOfPages) {
        this.authors = authors;
        this.title = title;
        this.numberOfPages = numberOfPages;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authors, title, numberOfPages);
    }
}

public class Repository {
    private final List<Book> list = new ArrayList<>();

    public void add(Book b) {
        this.list.add(b);
    }

    public void remove(Book b) {
        this.list.remove(b);
    }

    public int size() {
        return this.list.size();
    }
}
