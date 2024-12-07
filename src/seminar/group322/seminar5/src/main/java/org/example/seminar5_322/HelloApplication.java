package org.example.seminar5_322;

import Domain.Player;
import Repository.IRepository;
import Repository.PlayerDbRepository;
import Repository.RepositoryException;
import Service.PlayerService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, RepositoryException {

        //for sqlite: use org.xerial:sqlite-jdbc:3.46.0.0 or 3.47.1.0
        IRepository<Player> repository = new PlayerDbRepository();
        PlayerService service = new PlayerService(repository);


        //lista de jucatori
        ListView<Player> playerListView = new ListView<>();

        //https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ObservableList.html
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList(service.getALL());
        playerListView.setItems(playerObservableList);

        //butoanele care ne vor permite sa efectuam actiuni
        //de adaugare, stergere, update (update - not implemented)
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");

        //Horizontal box: layout in care asezam componentele
        //pe o linie, pe orizontala

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(addButton);
        buttonBox.getChildren().add(deleteButton);
        buttonBox.getChildren().add(updateButton);
        buttonBox.setSpacing(10);

        //aranjam butoanele in HBox sa fie centrate
        buttonBox.setAlignment(Pos.BASELINE_CENTER);

        //"Etichete" care vor aparea in stanga field-urilor
        //de completat, pentru a sti ce informatii sa
        //introducem in fiecare
        Label idLabel = new Label("ID");
        Label nameLabel = new Label("Name");
        Label ageLabel = new Label("Age");
        Label playingPosLabel = new Label("Playing position");

        //campurile in care introducem informatiile
        //despre jucatori
        TextField idTextField = new TextField();
        TextField nameTextField = new TextField();
        TextField ageTextField = new TextField();
        //TextField playingPosTextField = new TextField();

        //alternativ, use a comboBox:
        //https://jenkov.com/tutorials/javafx/combobox.html
        ComboBox<String> playingPosCombo = new ComboBox<>();
        playingPosCombo.getItems().add("goalkeeper");
        playingPosCombo.getItems().add("defender");
        playingPosCombo.getItems().add("midfielder");
        playingPosCombo.getItems().add("forward");


        //https://jenkov.com/tutorials/javafx/gridpane.html
        //https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/layout/GridPane.html
        //folosim un grid pentru label-uri si text field-uri

        GridPane labelsAndFieldsPane = new GridPane();
        labelsAndFieldsPane.add(idLabel, 0, 0);
        labelsAndFieldsPane.add(idTextField, 1, 0);
        labelsAndFieldsPane.add(nameLabel, 0, 1);
        labelsAndFieldsPane.add(nameTextField, 1, 1);
        labelsAndFieldsPane.add(ageLabel, 0, 2);
        labelsAndFieldsPane.add(ageTextField, 1, 2);
        labelsAndFieldsPane.add(playingPosLabel, 0, 3);
        labelsAndFieldsPane.add(playingPosCombo, 1, 3);

        labelsAndFieldsPane.setHgap(2);
        labelsAndFieldsPane.setVgap(5);


        //cream un tabel in care vom afisa datele
        TableView<Player> playerTableView = new TableView<>();

        //cream cate o coloana pe rand
        //textul din paranteze este header-ul
        TableColumn<Player, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Player, Integer> ageColumn = new TableColumn<>("Age");
        TableColumn<Player, String> playingPositionColumn = new TableColumn<>("Playing position");

        //specificam cum se vor completa coloanele - ce camp dintr-un
        //obiect Player vine pe fiecare coloana
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/PropertyValueFactory.html
        //second paragraph, first sentence: [...] there is fall-through support for attempting to call
        //get<property>() or is<property>()
        //pentru noi functioneaza fiindca avem getId, getName, getAge, getPlayingPosition
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        playingPositionColumn.setCellValueFactory(new PropertyValueFactory<>("playingPosition"));

        //adaugam coloanele la tabel
        playerTableView.getColumns().add(idColumn);
        playerTableView.getColumns().add(nameColumn);
        playerTableView.getColumns().add(ageColumn);
        playerTableView.getColumns().add(playingPositionColumn);

        playerTableView.setItems(playerObservableList);


        Label yearLabel = new Label("Select a year:");

        //adaugat de la seminar, exemplu cu Slider
        //valoarea selectata de pe slider
        //se afiseaza cand apasam pe butonul de Add
        //dar nu o utilizam nicaieri (doar pentru exemplu)
        //https://jenkov.com/tutorials/javafx/slider.html
        Slider yearSlider = new Slider();
        yearSlider.setMin(1975);
        yearSlider.setMax(2024);
        yearSlider.setShowTickLabels(true);
        yearSlider.setMajorTickUnit(1);

        //adaugat de la seminar, exemplu RadioButton
        //https://jenkov.com/tutorials/javafx/radiobutton.html
        //ToggleGroup:
        //https://jenkov.com/tutorials/javafx/radiobutton.html#togglegroup
        //se identifica RadioButton selectat cand se apasa butonul de sort
        ToggleGroup radioToggleGroup = new ToggleGroup();

        RadioButton sortAscendingRadioButton = new RadioButton("Sorteaza crescator");
        RadioButton sortDescendingRadioButton = new RadioButton("Sorteaza descrescator");
        sortAscendingRadioButton.setToggleGroup(radioToggleGroup);
        sortDescendingRadioButton.setToggleGroup(radioToggleGroup);
        Button sortButton = new Button("Sort players");


        //layout principal
        HBox mainBox = new HBox();

        //Vertical Box
        //https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/layout/VBox.html
        //componentele sunt aranjate pe o coloana, pe verticala
        //rightSideBox este unde vom pune label-urile si text field-urile
        //si butoanele (+slider, radioButtons)
        VBox rightSideBox = new VBox();
        rightSideBox.getChildren().add(buttonBox);
        rightSideBox.getChildren().add(labelsAndFieldsPane);
        rightSideBox.getChildren().add(yearLabel);
        rightSideBox.getChildren().add(yearSlider);
        rightSideBox.getChildren().add(sortAscendingRadioButton);
        rightSideBox.getChildren().add(sortDescendingRadioButton);
        rightSideBox.getChildren().add(sortButton);
        rightSideBox.setSpacing(20);

        mainBox.getChildren().add(playerListView);
        mainBox.getChildren().add(rightSideBox);
        mainBox.setSpacing(15);
        mainBox.getChildren().add(playerTableView);


        //cand apasam butonul addButon, se executa metoda handle
        //care preia continutul din textField-uri
        //si se foloseste de service ca sa adauge un Player
        //cu datele din textField-uri (la fel cum vechiul ui se folosea de service
        //sa adauge ce se citea de la tastatura)
        //schimbam continutul playerList prin setAll la noua lista, cu player adaugat
        //daca se arunca o eroare (fie la conversia in int, fie
        //pentru ID duplicat, se afiseaza mesaj de eroare - Alert)

        //ADDED: afisam in consola valoarea selectata de pe slider
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String name = nameTextField.getText();
                    int age = Integer.parseInt(ageTextField.getText());
                    String playingPosition = playingPosCombo.getSelectionModel().getSelectedItem();
                    int year = (int) yearSlider.getValue();
                    System.out.println("Year selected on slider is: " + year);
                    service.adaugare(id, name, age, playingPosition);
                    playerObservableList.setAll(service.getALL());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }

            }
        });
        //vrem ca atunci cand dam click pe o linie din lista, text field-urile
        //sa se populeze cu informatiile despre player-ul din linia respectiva
        playerListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Player selectedPlayer = playerListView.getSelectionModel().getSelectedItem();
                if (selectedPlayer != null) {
                    idTextField.setText(String.valueOf(selectedPlayer.getId()));
                    nameTextField.setText(selectedPlayer.getName());
                    ageTextField.setText(String.valueOf(selectedPlayer.getAge()));
                    playingPosCombo.getSelectionModel().select(selectedPlayer.getPlayingPosition());
                } else {
                    System.out.println("You're clicking on the list, but no player is selected.");
                }

            }
        });

        //Simona Petian
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int id = Integer.parseInt(idTextField.getText());
                try {
                    service.remove(id);
                    playerObservableList.setAll(service.getALL());
                } catch (RepositoryException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        sortButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RadioButton selectedRadioButton =
                        (RadioButton) radioToggleGroup.getSelectedToggle();
                if (selectedRadioButton != null) {
                    System.out.println(selectedRadioButton.getText());
                }
            }
        });

        //permite selectarea simultana a mai multe randuri din tabel
        //spre exemplu, CTRL+click mai multe randuri


        playerTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //selectarea din tabel nu este conectata la afisarea
        //in text field-uri, doar se afiseaza in consola lista
        //de jucatori selectati (inclus pentru exemplu)
        playerTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //luam din tabel lista de players selectati, afisam informatii despre ei (in consola)
                ObservableList<Player> selectedPlayers = playerTableView.getSelectionModel().getSelectedItems();
                System.out.println("Selected players are:");
                for (Player p : selectedPlayers) {
                    System.out.println(p);
                }
            }
        });

        //make the list stretch in the available space
        //https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/layout/HBox.html#setHgrow(javafx.scene.Node,javafx.scene.layout.Priority)
        HBox.setHgrow(playerListView, Priority.ALWAYS);
        Scene scene = new Scene(mainBox, 1000, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}