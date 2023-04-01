package net.golbarg.kankor.view.exam;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import net.golbarg.kankor.controller.SystemController;
import net.golbarg.kankor.db.TableExamResult;
import net.golbarg.kankor.model.ExamResult;

import java.net.URL;
import java.util.ArrayList;
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


    TableExamResult tableExamResult;
    final String[] types = new String[] { "همه", "آی دی", "نام", "مکتب", "ولایت", };
    ObservableList<String> typeList = FXCollections.observableArrayList(types);
    ObservableList<String> resultOfList = FXCollections.observableArrayList(new String[]{"مکتب", "کانکور"});
    ArrayList<ExamResult> resultList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableExamResult = new TableExamResult();

        initTableColumns();
        initSearchTypes();

        resultList = tableExamResult.getAll();

        tableViewExamResults.getItems().addAll(resultList);
    }

    private void initTableColumns() {
        columnFullName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamResult, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamResult, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>(String.valueOf(
                        param.getValue().getExam().getUser().getName() + " " + param.getValue().getExam().getUser().getLastName()));
            }
        });

        columnSchool.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamResult, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamResult, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>(param.getValue().getExam().getUser().getSchoolName());
            }
        });

        columnProvince.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamResult, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamResult, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getExam().getUser().getLocation().getPersianName());
            }
        });

        columnScore.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamResult, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamResult, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getCorrectAnswerCount().getScore() + "");
            }
        });

        columnAcceptedField.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamResult, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamResult, String> param) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(param.getValue().getPassedField()));
            }
        });

        columnExamDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamResult, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamResult, String> param) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(param.getValue().getExam().getDate()));
            }
        });
    }

    private void initSearchTypes() {
        comboSearchType.setItems(typeList);
        comboSearchType.getSelectionModel().selectFirst();

        comboResultsOf.setItems(resultOfList);
        comboResultsOf.getSelectionModel().selectFirst();
        comboResultsOf.setDisable(true);
    }

}