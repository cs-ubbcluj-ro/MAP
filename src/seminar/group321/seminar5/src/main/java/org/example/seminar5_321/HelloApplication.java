package org.example.seminar5_321;

import domain.Musician;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.IRepository;
import repository.SQLMusicianRepository;
import service.MusicianService;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        IRepository<Musician> dbRepo = new SQLMusicianRepository();
        MusicianService service = new MusicianService(dbRepo);

        //Add table, labels, text fields, buttons
        //Use: HBox, VBox, GridPane

        //Puteti citi mai mult la: https://jenkov.com/tutorials/javafx/tableview.html
        //(un exemplu de documentatie+exemple, se pot folosi si altele)

        //Horizontal box: layout in care asezam componentele
        //pe o linie, pe orizontala

        //layout-ul principal
        HBox mainBox = new HBox();

        //cream un tabel in care vom afisa datele
        TableView<Musician> musicianTable = new TableView<>();

        //cream cate o coloana pe rand
        //textul din paranteze este header-ul
        TableColumn<Musician, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Musician, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Musician, Integer> ageColumn = new TableColumn<>("Age");
        TableColumn<Musician, String> genreColumn = new TableColumn<>("Genre");

        //specificam cum se vor completa coloanele - ce camp dintr-un
        //obiect Musician vine pe fiecare coloana
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/PropertyValueFactory.html
        //second paragraph, first sentence: [...] there is fall-through support for attempting to call
        //get<property>() or is<property>()
        //pentru noi functioneaza fiindca avem getId, getName, getAge, getGenre
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        //adaugam coloanele la tabel
        musicianTable.getColumns().add(idColumn);
        musicianTable.getColumns().add(nameColumn);
        musicianTable.getColumns().add(ageColumn);
        musicianTable.getColumns().add(genreColumn);


        //cream o lista de musicians care se vor afisa in tabel
        //https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ObservableList.html
        ObservableList<Musician> musicianList = FXCollections.observableArrayList(service.getAll());
        musicianTable.setItems(musicianList);

        //Vertical Box
        //https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/layout/VBox.html
        //componentele sunt aranjate pe o coloana, pe verticala
        //rightSideBox este unde vom pune label-urile si text field-urile
        //si butoanele

        VBox rightSideBox = new VBox();


        //https://jenkov.com/tutorials/javafx/gridpane.html
        //https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/layout/GridPane.html
        //folosim un grid pentru label-uri si text field-uri

        //modificat nume componente de la seminar prin adaugare tip
        //componenta (label, text field etc) pentru a le identifica mai usor cand le folosim
        GridPane labelsAndFieldsPane = new GridPane();
        labelsAndFieldsPane.setVgap(3.5);
        labelsAndFieldsPane.setHgap(3.5);
        Label idLabel = new Label("ID");
        Label nameLabel = new Label("Name");
        Label ageLabel = new Label("Age");
        Label genLabel = new Label("Gen");
        TextField idTextField = new TextField();
        TextField nameTextField = new TextField();
        TextField ageTextField = new TextField();
        TextField genTextField = new TextField();

        labelsAndFieldsPane.add(idLabel, 0, 0);
        labelsAndFieldsPane.add(idTextField, 1, 0);
        labelsAndFieldsPane.add(nameLabel, 0, 1);
        labelsAndFieldsPane.add(nameTextField, 1, 1);
        labelsAndFieldsPane.add(ageLabel, 0, 2);
        labelsAndFieldsPane.add(ageTextField, 1, 2);
        labelsAndFieldsPane.add(genLabel, 0, 3);
        labelsAndFieldsPane.add(genTextField, 1, 3);


        rightSideBox.getChildren().add(labelsAndFieldsPane);

        //new HBox pentru butoane
        HBox buttonBox = new HBox();
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");

        buttonBox.getChildren().add(addButton);
        buttonBox.getChildren().add(deleteButton);
        buttonBox.getChildren().add(updateButton);

        //aranjam butoanele in HBox sa fie centrate
        //https://openjfx.io/javadoc/13/javafx.graphics/javafx/geometry/Pos.html#BASELINE_CENTER
        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.setSpacing(10);


        rightSideBox.getChildren().add(buttonBox);


        //set some space in between rightSideBox components
        //which are the grid pane and the buttonBox
        rightSideBox.setSpacing(15);

        mainBox.getChildren().add(musicianTable);
        mainBox.getChildren().add(rightSideBox);

        //adaugam si un listView (doar pentru exercitiu)
        ListView<Musician> musicianListView = new ListView<>();
        musicianListView.setItems(musicianList);

        //make the list stretch in the available space
        musicianListView.setMaxWidth(Double.MAX_VALUE);
        //https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/layout/HBox.html#setHgrow(javafx.scene.Node,javafx.scene.layout.Priority)
        HBox.setHgrow(musicianListView, Priority.ALWAYS);


        mainBox.getChildren().add(musicianListView);

        mainBox.setPadding(new Insets(10));
        mainBox.setSpacing(50);


        //cand apasam butonul addButon, se executa metoda handle
        //care preia continutul din textField-uri
        //si se foloseste de service ca sa adauge un Musician
        //cu datele din textField-uri (la fel cum vechiul ui se folosea de service
        //sa adauge ce se citea de la tastatura)
        //schimbam continutul musicianList prin setAll la noua lista, cu musician adaugat
        //daca se arunca o eroare (fie la conversia in int, fie
        //pentru ID duplicat, se afiseaza mesaj de eroare - Alert)
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    int id = Integer.parseInt(idTextField.getText());
                    String name = nameTextField.getText();
                    int age = Integer.parseInt(ageTextField.getText());
                    String genre = genTextField.getText();
                    service.add(id, name, age, genre);
                    musicianList.setAll(service.getAll());
                } catch (Exception e ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText("A aparut o eroare: "+e.getMessage());
                    alert.show();
                }
                System.out.println("We pressed the add button.");
            }
        });

//        //permite selectarea simultana a mai multe randuri din tabel
//        //spre exemplu, CTRL+click mai multe randuri
//        //la seminar nu mergea pentru ca scriam getSelectionModel().setSelectionModel (Model in loc de Mode
//        //la al doilea apel - insert facepalm emoji :) )
//        musicianTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//        musicianTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                //luam din tabel lista de musicians selectati, afisam informatii despre ei (in consola)
//                ObservableList<Musician> selectedMusicians = musicianTable.getSelectionModel().getSelectedItems();
//                System.out.println("Selected musicians are:");
//                for (Musician m: selectedMusicians){
//                    System.out.println(m);
//                }
//            }
//        });

        //cu aceasta setare, putem selecta o singura linie din tabel
        musicianTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //vrem ca atunci cand dam click e o linie din tabel, text field-urile
        //sa se populeze cu informatiil despre musician-ul din linia respectiva
        musicianTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Musician selectedMusician = musicianTable.getSelectionModel().getSelectedItem();
                idTextField.setText(String.valueOf(selectedMusician.getId()));
                nameTextField.setText(selectedMusician.getName());
                ageTextField.setText(String.valueOf(selectedMusician.getAge()));
                genTextField.setText(selectedMusician.getGenre());
            }
        });

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    //int idToDelete = Integer.parseInt(idTextField.getText());
                    //alternativ, ca sa stergem musician selectat din tabel
                    Musician selectedMusician = musicianTable.getSelectionModel().getSelectedItem();
                    int idToDelete = selectedMusician.getId();
                    service.remove(idToDelete);
                    musicianList.setAll(service.getAll());
                } catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText("A aparut o eroare: "+e.getMessage());
                    alert.show();
                }
            }
        });
        Scene scene = new Scene(mainBox, 1000, 400);
        stage.setTitle("Music App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}