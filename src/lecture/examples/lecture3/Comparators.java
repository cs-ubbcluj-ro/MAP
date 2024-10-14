package lecture.examples.lecture3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Book implements Comparable<Book> {
    private final String authors;
    private final String title;
    private final int numberOfPages;

    public Book(String authors, String title, int numberOfPages) {
        this.authors = authors;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title);
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
}

class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getAuthors().compareTo(o2.getAuthors());
    }
}

class PagesComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getNumberOfPages() < o2.getNumberOfPages())
            return -1;
        else if (o1.getNumberOfPages() > o2.getNumberOfPages())
            return 1;
        else
            return 0;
    }
}

public class Comparators {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Heather Morris", "The Tattooist of Auschwitz", 250));
        books.add(new Book("Andre Agassi", "Open", 300));
        books.add(new Book("Aaron Courville, Ian Goodfellow, Yoshua Bengio", "Deep Learning", 700));

        // sorting - only works if Book implements Comparable
        Collections.sort(books);
        for (Book b : books)
            System.out.println(b);

        System.out.println("--------------------------------------");

        // sort by author
        books.sort(new AuthorComparator());
        for (Book b : books)
            System.out.println(b);

        System.out.println("--------------------------------------");

        // sort by number of pages
        books.sort(new PagesComparator());
        for (Book b : books)
            System.out.println(b);
    }
}
