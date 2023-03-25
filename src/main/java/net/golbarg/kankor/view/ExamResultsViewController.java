package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import net.golbarg.kankor.db.TableExam;
import net.golbarg.kankor.model.ExamResult;
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
    private TableView<ExamResult> tableViewExamResults;
    @FXML
    private TableColumn<ExamResult, String> columnNumber;
    @FXML
    private TableColumn<ExamResult, String> columnFullName;
    @FXML
    private TableColumn<ExamResult, String> columnSchool;
    @FXML
    private TableColumn<ExamResult, String> columnProvince;
    @FXML
    private TableColumn<ExamResult, String> columnScore;
    @FXML
    private TableColumn<ExamResult, String> columnAcceptedField;
    @FXML
    private TableColumn<ExamResult, String> columnExamDate;


    Table
    final String[] types = new String[] { "همه", "آی دی", "نام", "مکتب", "ولایت", };
    ObservableList<String> typeList = FXCollections.observableArrayList(types);
    ObservableList<String> resultOfList = FXCollections.observableArrayList(new String[]{"کانکور", "مکتب"});
    ObservableList<ExamResult> resultList = FXCollections.observableArrayList();

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