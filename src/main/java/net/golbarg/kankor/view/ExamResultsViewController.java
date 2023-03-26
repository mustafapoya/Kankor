package net.golbarg.kankor.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import net.golbarg.kankor.db.TableExam;
import net.golbarg.kankor.model.Exam;
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
    private TableView<Exam> tableViewExamResults;
    @FXML
    private TableColumn<Exam, String> columnNumber;
    @FXML
    private TableColumn<Exam, String> columnFullName;
    @FXML
    private TableColumn<Exam, String> columnSchool;
    @FXML
    private TableColumn<Exam, String> columnProvince;
    @FXML
    private TableColumn<Exam, String> columnScore;
    @FXML
    private TableColumn<Exam, String> columnAcceptedField;
    @FXML
    private TableColumn<Exam, String> columnExamDate;


    TableExam tableExam;
    final String[] types = new String[] { "همه", "آی دی", "نام", "مکتب", "ولایت", };
    ObservableList<String> typeList = FXCollections.observableArrayList(types);
    ObservableList<String> resultOfList = FXCollections.observableArrayList(new String[]{"کانکور", "مکتب"});
    ObservableList<Exam> resultList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableExam = new TableExam();

        initTableColumns();

        initSearchTypes();

        tableViewExamResults.getItems().addAll(tableExam.getAll());
    }

    private void initTableColumns() {
        columnFullName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>(String.valueOf(param.getValue().getUserId()));
            }
        });

        columnSchool.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>("school name");
            }
        });

        columnProvince.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                return new ReadOnlyObjectWrapper<>("province");
            }
        });

        columnScore.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getTotalScore() + "");
            }
        });

        columnAcceptedField.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(param.getValue().getPassedField()));
            }
        });

        columnExamDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(param.getValue().getExamDate()));
            }
        });
    }

    private void initSearchTypes() {
        comboSearchType.setItems(typeList);
        comboSearchType.getSelectionModel().selectFirst();

        comboResultsOf.setItems(resultOfList);
        comboResultsOf.getSelectionModel().selectFirst();
    }

}