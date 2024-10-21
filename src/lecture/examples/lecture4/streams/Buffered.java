package lecture.examples.lecture4.streams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        return "Book{" + "authors='" + authors + '\'' + ", title='" + title + '\'' + ", numberOfPages=" + numberOfPages + '}';
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
}

public class Buffered {
    static List<Book> createBooks(String filename) {
        List<Book> books = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] elems = line.split("[|]");
                if (elems.length < 3) continue;
                Book b = new Book(elems[0].strip(), elems[1].strip(), Integer.parseInt(elems[2].strip()));
                books.add(b);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) try {
                br.close();
            } catch (IOException e) {
                System.out.println("Error while closing the file " + e);
            }
        }
        return books;
    }

    static void writeBooks(List<Book> books, String filename) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (Book b : books) {
                bw.write(b.getAuthors() + " | " + b.getTitle() + " | " + b.getNumberOfPages());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<Book> books = createBooks("src/lecture/examples/lecture4/streams/books_in.txt");
        for (Book b : books)
            System.out.println(b);

        List<Book> booksToWrite = new ArrayList<>();
        booksToWrite.add(new Book("Jordan Ellenberg", "How not to be wrong. The hidden maths of everday life", 470));
        booksToWrite.add(new Book("Tom Mitchell", "Machine Learning", 580));
        writeBooks(booksToWrite, "src/lecture/examples/lecture4/streams/books_out.txt");
    }

}
