package lecture.examples.lecture7;

class Student {
    private final int id;
    private String name;
    private final double average;

    public Student(int id, String name, double average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", average=" + average +
                '}';
    }
}

class Discipline {
    private final int id;
    private final String name;

    public Discipline(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class StudentDiscipline {
    private final Student student;
    private final Discipline discipline;

    public StudentDiscipline(Student student, Discipline discipline) {
        this.student = student;
        this.discipline = discipline;
    }

    public int getId() {
        return Integer.parseInt(Integer.toString(student.getId()) + Integer.toString(discipline.getId()));
    }

    public Student getStudent() {
        return student;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public String toString() {
        return "StudentDiscipline{" +
                "student=" + student +
                ", discipline=" + discipline +
                '}';
    }
}

class StudentDisciplineIds {
    private final int studentId;
    private final int disciplineId;

    public StudentDisciplineIds(int studentId, int disciplineId) {
        this.studentId = studentId;
        this.disciplineId = disciplineId;
    }

    public int getId() {
        return Integer.parseInt(Integer.toString(studentId) + Integer.toString(disciplineId));
    }

    public int getStudentId() {
        return studentId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }
}