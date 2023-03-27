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
import net.golbarg.kankor.db.TableExam;
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.UniversityFaculty;

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
    ObservableList<String> resultOfList = FXCollections.observableArrayList(new String[]{"مکتب", "کانکور"});
    ArrayList<Exam> resultList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableExam = new TableExam();

        initTableColumns();
        initSearchTypes();

        resultList = tableExam.getAll();

        for(Exam e : resultList) {
            e.setUser(SystemController.currentUser);
        }

        tableViewExamResults.getItems().addAll(resultList);
    }

    private void initTableColumns() {
        columnFullName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>(String.valueOf(
                        param.getValue().getUser().getName() + " " + param.getValue().getUser().getLastName()));
            }
        });

        columnSchool.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>(param.getValue().getUser().getSchoolName());
            }
        });

        columnProvince.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exam, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exam, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getUser().getLocation().getPersianName());
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
        comboResultsOf.setDisable(true);
    }

}