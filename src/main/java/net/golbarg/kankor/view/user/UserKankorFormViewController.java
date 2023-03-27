package net.golbarg.kankor.view.user;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.control.Alert.*;
import net.golbarg.kankor.controller.DialogController;
import net.golbarg.kankor.controller.ValidationController;
import net.golbarg.kankor.custom.CellFactorySample;
import net.golbarg.kankor.db.TableLocation;
import net.golbarg.kankor.db.TableUser;
import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Language;
import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.User;
import net.golbarg.kankor.view.exam.component.CharacterView;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class UserKankorFormViewController implements Initializable {
    // first Section which include User image and it's ID
    @FXML
    private ImageView imgUserImage;
    @FXML
    private TextField txtKankorId;

    // Second Section which include User property
    @FXML
    private GridPane gridPaneCell;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFatherName;
    @FXML
    private TextField txtGrandFatherName;

    // Fourth section which include original location
    @FXML
    private ComboBox<Location> comboOriginalProvince;
    @FXML
    private TextField txtOriginalDistrict;
    @FXML
    private TextField txtOriginalVillage;

    // Third Section which include current Location
    @FXML
    private ComboBox<Location> comboCurrentProvince;
    @FXML
    private TextField txtCurrentDistrict;
    @FXML
    private TextField txtCurrentVillage;

    // Fifth Section table
    @FXML
    private TextField txtTazkira;
    @FXML
    private TextField txtSchoolName;
    @FXML
    private TextField txtGraduationYear;
    @FXML
    private ComboBox<Gender> comboGender;
    @FXML
    private ComboBox<Language> comboLanguage;
    @FXML
    private Text txtStatusMessage;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    //
    private User user;
    private File selectedUserImage;
    ObservableList<Location> provinceList = FXCollections.observableArrayList();
    ObservableList<Gender> genderList = FXCollections.observableArrayList();
    ObservableList<Language> languageList = FXCollections.observableArrayList();

    ObservableList<CharacterView> characterColumns = FXCollections.observableArrayList();
    // generate id
    Random rand = new Random();
    private int kankorId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateID();
        user = new TableUser().findById(1);

        provinceList.addAll(new TableLocation().getAllProvince());

        comboOriginalProvince.setItems(provinceList);
        comboCurrentProvince.setItems(provinceList);

        Callback<ListView<Location>, ListCell<Location>> cellFactory = CellFactorySample.getComboBoxLocation(null, 0);

        comboOriginalProvince.setButtonCell(cellFactory.call(null));
        comboOriginalProvince.setCellFactory(cellFactory);
        comboOriginalProvince.getSelectionModel().selectFirst();

        comboCurrentProvince.setButtonCell(cellFactory.call(null));
        comboCurrentProvince.setCellFactory(cellFactory);
        comboCurrentProvince.getSelectionModel().selectFirst();

        // gender
        genderList.addAll(Gender.getAll());
        comboGender.setItems(genderList);
        Callback<ListView<Gender>, ListCell<Gender>> cellFactoryGender = CellFactorySample.getComboBoxGender(null, 0);

        comboGender.setButtonCell(cellFactoryGender.call(null));
        comboGender.setCellFactory(cellFactoryGender);
        comboGender.getSelectionModel().selectFirst();

        //language
        languageList.addAll(Language.getAll());
        comboLanguage.setItems(languageList);
        Callback<ListView<Language>, ListCell<Language>> cellFactoryLanguage = CellFactorySample.getComboBoxLanguage(null, 0);

        comboLanguage.setButtonCell(cellFactoryLanguage.call(null));
        comboLanguage.setCellFactory(cellFactoryLanguage);
        comboLanguage.getSelectionModel().selectFirst();

        //
        createCharacterColumns(4);
        addToGridPane();
        limitTextFieldCharacterNumber();
        initializeUserFields();

        //
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                handleSave();
            }
        });
    }

    public void createCharacterColumns(int n) {
        for (int i = 0; i < n; i++) {
            characterColumns.add(new CharacterView());
        }
    }

    public void addToGridPane() {
        gridPaneCell.add(characterColumns.get(0), 2, 0);
        gridPaneCell.add(characterColumns.get(1), 2, 1);
        gridPaneCell.add(characterColumns.get(2), 2, 2);
        gridPaneCell.add(characterColumns.get(3), 2, 3);
    }

    public void limitTextFieldCharacterNumber() {
        // First Text Field -> Name
        txtName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String value = CharacterView.cleanText(newValue);
                if (value.length() > 12) {
                    value = value.substring(0, 12);
                }
                characterColumns.get(0).initializeText(value);
                characterColumns.get(0).updateText(value);
            }
        });
        // Second Text Field ->
        txtLastName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String value = CharacterView.cleanText(newValue);
                if (value.length() > 12) {
                    value = value.substring(0, 12);
                }
                characterColumns.get(1).initializeText(value);
                characterColumns.get(1).updateText(value);

            }
        });
        // Third Text Field ->
        txtFatherName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String value = CharacterView.cleanText(newValue);
                if (value.length() > 12) {
                    value = value.substring(0, 12);
                }
                characterColumns.get(2).initializeText(value);
                characterColumns.get(2).updateText(value);

            }
        });
        // Fourth Text Field ->
        txtGrandFatherName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String value = CharacterView.cleanText(newValue);
                if (value.length() > 12) {
                    value = value.substring(0, 12);
                }
                characterColumns.get(3).initializeText(value);
                characterColumns.get(3).updateText(value);

            }
        });
    }

    public void initializeUserFields(){
        txtName.setText(user.getName());
        characterColumns.get(0).initializeText(user.getName());
        txtLastName.setText(user.getLastName());
        characterColumns.get(1).initializeText(user.getLastName());
        txtSchoolName.setText(user.getSchoolName());
    }

    /** TODO: implement the saving functionality */
    public void handleSave() {
        String name = "", lastName = "", fatherName = "", grandFatherName = "";
        Location currentProvince, originalProvince;
        String originalWolosvali = "", originalQarya = "";
        String currentWolosvali = "", currentQarya = "";
        String tazkiraID = "";
        String school = "", graduationYear = "";

        if (ValidationController.isInputJustText(txtName.getText())) {
            name = txtName.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation",
                    "Invalid Data","please enter a valid name [ " + txtName.getText() + " ]");
            txtName.setText("");
            txtName.requestFocus();
            return;
        }

        // validation of Last Name Field
        if (ValidationController.isInputJustText(txtLastName.getText())) {
            lastName = txtLastName.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid Last Name [ " + txtLastName.getText() + " ]");
            txtLastName.setText("");
            txtLastName.requestFocus();
            return;
        }

        // validation of Father name Text Field
        if (ValidationController.isInputJustText(txtFatherName.getText())) {
            fatherName = txtFatherName.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid Father name [ " + txtFatherName.getText() + " ]");
            txtFatherName.setText("");
            txtFatherName.requestFocus();
            return;
        }

        // validation of Grand Father Name Text Field
        if (ValidationController.isInputJustText(txtGrandFatherName.getText())) {
            grandFatherName = txtGrandFatherName.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid Grand Father Name [ " + txtGrandFatherName.getText() + " ]");
            txtGrandFatherName.setText("");
            txtGrandFatherName.requestFocus();
            return;
        }

        currentProvince = comboOriginalProvince.getSelectionModel().getSelectedItem();
        if (ValidationController.isInputJustText(txtOriginalDistrict.getText())) {
            originalWolosvali = txtOriginalDistrict.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid District Name [" + txtOriginalDistrict.getText() + " ]");
            txtOriginalDistrict.setText("");
            txtOriginalDistrict.requestFocus();
            return;
        }

        if (ValidationController.isInputJustText(txtOriginalVillage.getText())) {
            originalQarya = txtOriginalVillage.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid Wolosvali Name [" + txtOriginalVillage.getText() + " ]");
            txtOriginalVillage.setText("");
            txtOriginalVillage.requestFocus();
            return;
        }

        currentProvince = comboCurrentProvince.getSelectionModel().getSelectedItem();
        if (ValidationController.isInputJustText(txtCurrentDistrict.getText())) {
            currentWolosvali = txtCurrentDistrict.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid Wolosvali Name [" + txtCurrentDistrict.getText() + " ]");
            txtCurrentDistrict.setText("");
            txtCurrentDistrict.requestFocus();
            return;
        }

        if (ValidationController.isInputJustText(txtCurrentVillage.getText())) {
            currentQarya = txtCurrentVillage.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid Wolosvali Name [" + txtCurrentVillage.getText() + " ]");
            txtCurrentVillage.setText("");
            txtCurrentVillage.requestFocus();
            return;
        }

        // Validation of Tazkira Values
        if (ValidationController.isInputJustNumber(txtTazkira.getText())) {
            tazkiraID = txtTazkira.getText();
        } else {
            DialogController.showMessage( AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a valid General number [" + txtTazkira.getText() + " ]");
            txtTazkira.setText("");
            txtTazkira.requestFocus();
            return;
        }

        // Validate School and GraduationYear
        if (ValidationController.isInputJustText(txtSchoolName.getText())) {
            school = txtSchoolName.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please eneter a vlaid School [ " + txtSchoolName.getText() + " ]");
            txtSchoolName.setText("");
            txtSchoolName.requestFocus();
            return;
        }

        if (ValidationController.isInputJustNumber(txtGraduationYear.getText())) {
            graduationYear = txtGraduationYear.getText();
        } else {
            DialogController.showMessage(AlertType.ERROR, "Validation", "Invalid Data",
                    "Please enter a Valid Number [ " + txtGraduationYear.getText() + " ]");
            txtGraduationYear.setText("");
            txtGraduationYear.requestFocus();
            return;
        }

        /** TODO: implement Saving of this data in DB (Kankor Form Table) */
        

    }

    public void generateID() {
        txtKankorId.setEditable(false);
        txtKankorId.setStyle("-fx-background-color:#cdcdcd;-fx-font-weight: bold;");
        kankorId = (100000 + rand.nextInt(999999));
        txtKankorId.setText(Integer.toString(kankorId));
    }

}
