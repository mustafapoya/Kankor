package net.golbarg.kankor.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import net.golbarg.kankor.custom.CellFactorySample;
import net.golbarg.kankor.db.TableUniversityFaculty;
import net.golbarg.kankor.db.TableUniversity;
import net.golbarg.kankor.model.UniversityFaculty;
import net.golbarg.kankor.model.University;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UniversityFormViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private ComboBox<University> comboUniversity;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    /**
     * Left Section Selecting of University Codes
     */
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
     * Accessing the Anchor Panes that are inside the TitledPane
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

    // Table
    @FXML
    private TableView<UniversityFaculty> tableViewUniversity;
    @FXML
    private TableColumn<UniversityFaculty, String> columnUniversity;
    @FXML
    private TableColumn<UniversityFaculty, String> columnFaculty;
    @FXML
    private TableColumn<UniversityFaculty, String> columnDepartment;
    @FXML
    private TableColumn<UniversityFaculty, String> columnCodeNumber;
    @FXML
    private TableColumn<UniversityFaculty, String> columnAdmission;

    private ObservableList<FieldSelectionViewController> fieldSelectionList = FXCollections.observableArrayList();
    private ObservableList<UniversityFaculty> universityFacultyList = FXCollections.observableArrayList();
    ContextMenu entriesPopup;
    TableUniversity tableUniversity;
    TableUniversityFaculty tableUniversityFaculty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableUniversity = new TableUniversity();
        tableUniversityFaculty = new TableUniversityFaculty();

        //
        comboUniversity.getItems().addAll(tableUniversity.getAll());
        Callback<ListView<University>, ListCell<University>> cellFactoryUniversity
                = CellFactorySample.getComboBoxUniversity(null, 0);
        comboUniversity.setButtonCell(cellFactoryUniversity.call(null));
        comboUniversity.setCellFactory(cellFactoryUniversity);

        //
        accordionChoice.setPrefWidth(200);

        // init university table
        initTableColumns();

        //combo
        comboUniversity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<University>() {
            @Override
            public void changed(ObservableValue<? extends University> observableValue, University oldValue, University newValue) {
                if(newValue != null) {
                    universityFacultyList.clear();
                    universityFacultyList.addAll(tableUniversityFaculty.getFacultiesOf(newValue.getId()));
                    tableViewUniversity.getItems().clear();
                    tableViewUniversity.getItems().addAll(universityFacultyList);
                }
            }
        });
        comboUniversity.getSelectionModel().selectFirst();

        // search
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchFaculty(txtSearch.getText());
            }
        });

        txtSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)) {
                    searchFaculty(txtSearch.getText());
                }
            }
        });

        initializeFields();
    }

    public void initializeFields() {
        fieldSelectionList.clear();
        HBox [] choices = {hbChoice1, hbChoice2, hbChoice3, hbChoice4, hbChoice5};

        try {
            for(int i = 0; i < 5; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(UniversityFormViewController.class.getResource("field-selection-view.fxml"));
                HBox element = fxmlLoader.load();
                FieldSelectionViewController controller = fxmlLoader.getController();
                choices[i].getChildren().add(element);
                fieldSelectionList.add(controller);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void searchFaculty(String facultyName) {
        if(facultyName.length() > 1) {
            universityFacultyList.clear();
            universityFacultyList.addAll(tableUniversityFaculty.findByName(facultyName));

            tableViewUniversity.getItems().clear();
            tableViewUniversity.getItems().addAll(universityFacultyList);
        } else {
            System.err.println("Invalid Input");
            System.err.println("Please enter a valid Text Value in Search Field");
            txtSearch.requestFocus();
        }
    }

    private void initTableColumns() {
        columnUniversity.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UniversityFaculty, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UniversityFaculty, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>(param.getValue().getUniversity().getTitle());
            }
        });

        columnFaculty.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UniversityFaculty, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UniversityFaculty, String> param) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyObjectWrapper<>(param.getValue().getName());
            }
        });

        columnDepartment.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UniversityFaculty, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UniversityFaculty, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getDepartment());
            }
        });

        columnCodeNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UniversityFaculty, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UniversityFaculty, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getCode());
            }
        });

        columnAdmission.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UniversityFaculty, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UniversityFaculty, String> param) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(param.getValue().getAdmission()));
            }
        });
    }

    public ArrayList<String> getFieldsValue() {
        ArrayList<String> codes = new ArrayList<>();
        for (int i = 0; i < fieldSelectionList.size(); i++) {
            codes.add(fieldSelectionList.get(i).getSelectedFieldValue());
        }
        return codes;
    }

    public ObservableList<FieldSelectionViewController> getFields() {
        return fieldSelectionList;
    }

    public boolean hasDuplicate() {
        for (int i = 0; i < fieldSelectionList.size(); i++) {
            for (int j = i + 1; j < fieldSelectionList.size(); j++) {
                if (j != i && fieldSelectionList.get(i).equals(fieldSelectionList.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }


}
