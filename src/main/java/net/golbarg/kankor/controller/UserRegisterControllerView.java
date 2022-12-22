package net.golbarg.kankor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.golbarg.kankor.model.User;
import net.golbarg.kankor.util.FileChooseDialog;
import net.golbarg.kankor.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserRegisterControllerView implements Initializable {
    @FXML
    private ImageView imgUserProfile;
    @FXML
    private Button btnSelectImage;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtPasswordConfirm;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFatherName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox comboGender;
    @FXML
    private ComboBox comboProvince;
    @FXML
    private TextField txtSchoolName;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private User user;
    private File selectedUserImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new User();

        btnSelectImage.setOnAction(event -> {
            handleSelectImage(null);
        });


    }

    public void handleSelectImage(Stage stage) {
        FileChooseDialog dialog = new FileChooseDialog(stage, FileChooseDialog.IMAGE_FILTER, "Select Image");
        selectedUserImage = dialog.openDialog();
        if(selectedUserImage != null) {
            try {
                imgUserProfile.setImage(Util.convertFileToImage(selectedUserImage));
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }

}
