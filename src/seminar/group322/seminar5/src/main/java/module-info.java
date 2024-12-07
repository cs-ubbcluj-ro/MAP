module org.example.seminar5_322 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens Domain to javafx.base;
    opens org.example.seminar5_322 to javafx.fxml;
    exports org.example.seminar5_322;
}