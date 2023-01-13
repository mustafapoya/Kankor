package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import net.golbarg.kankor.custom.FieldSelection;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

public class UniversityFormViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private ComboBox<String> comboUniversity;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<String> tableUniversity;
    @FXML
    private TableColumn columnUniversity;
    @FXML
    private TableColumn columnFaculty;
    @FXML
    private TableColumn columnDepartment;
    @FXML
    private TableColumn columnCodeNumber;
    @FXML
    private TableColumn columnAdmission;

    @FXML
    private Accordion accordionChoice;
    @FXML
    private TitledPane titledPaneChoice1;
    @FXML
    private TitledPane titledPaneChoice2;
    @FXML
    private TitledPane titledPaneChoice3;
    @FXML
    private TitledPane titledPaneChoice4;
    @FXML
    private TitledPane titledPaneChoice5;

    /**
     * Accessing the Anchor Panes that are inside of the TitledPane
     */
    @FXML
    private HBox hbChoice1;
    @FXML
    private HBox hbChoice2;
    @FXML
    private HBox hbChoice3;
    @FXML
    private HBox hbChoice4;
    @FXML
    private HBox hbChoice5;

    private static ObservableList<FieldSelection> SelectionList = FXCollections.observableArrayList();
    private SortedSet<String> facultyList = new TreeSet<>();

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
