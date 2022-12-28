package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Location;

import java.net.URL;
import java.util.ResourceBundle;

public class KankorFormViewController implements Initializable {
    // first Section which include User image and it's ID
    @FXML
    private ImageView imgUserImage;
    @FXML
    private TextField txtKankorId;

    // Second Section which include User property
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
    private ComboBox<String> comboLanguage;
    @FXML
    private Text txtStatusMessage;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
