module org.example.seminar5_321 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires java.sql;

    opens domain to javafx.base;
    opens org.example.seminar5_321 to javafx.fxml;
    exports org.example.seminar5_321;
}