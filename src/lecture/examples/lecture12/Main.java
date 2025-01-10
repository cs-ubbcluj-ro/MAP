package lecture.examples.lecture12;

public class Main {
    public static void main(String[] args) {
        TextFileRepository repoStudents = new TextFileRepository("src/lecture/examples/lecture12/Student.txt");
        TextFileRepository repoBooks = new TextFileRepository("src/lecture/examples/lecture12/Book.txt");

        // print all students and all books
        for (WritableToCSV elem: repoStudents.getAll())
            System.out.println(elem);

        for (WritableToCSV elem: repoBooks.getAll())
            System.out.println(elem);

        // add a book and a student and the files will be changed
        repoBooks.addElement(new Book("William Shakespeare", "The tempest", 78));
        repoStudents.addElement(new Student(4, "Moldovan Darius", 8.8));
    }
}
