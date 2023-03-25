package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import net.golbarg.kankor.model.KankorResult;
import net.golbarg.kankor.model.UniversityFaculty;

import java.net.URL;
import java.util.ResourceBundle;

public class ExamResultsViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private ComboBox<String> comboResultsOf;
    @FXML
    private Button btnRefresh;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<String> comboSearchType;
    @FXML
    private Button btnSearch;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private TableView<UniversityFaculty> tableViewExamResults;
    @FXML
    private TableColumn<UniversityFaculty, String> columnNumber;
    @FXML
    private TableColumn<UniversityFaculty, String> columnFullName;
    @FXML
    private TableColumn<UniversityFaculty, String> columnSchool;
    @FXML
    private TableColumn<UniversityFaculty, String> columnProvince;
    @FXML
    private TableColumn<UniversityFaculty, String> columnScore;
    @FXML
    private TableColumn<UniversityFaculty, String> columnAcceptedField;
    @FXML
    private TableColumn<UniversityFaculty, String> columnExamDate;


    final String[] types = new String[] { "همه", "آی دی", "نام", "مکتب", "ولایت", };
    ObservableList<String> typeList = FXCollections.observableArrayList(types);
    ObservableList<String> resultOfList = FXCollections.observableArrayList(new String[]{"کانکور", "مکتب"});

    ObservableList<KankorResult> resultList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSearchTypes();
    }

    private void initSearchTypes() {
        comboSearchType.setItems(typeList);
        comboSearchType.getSelectionModel().selectFirst();

        comboResultsOf.setItems(resultOfList);
        comboResultsOf.getSelectionModel().selectFirst();
    }

}