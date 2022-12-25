package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.golbarg.kankor.controller.Util;
import net.golbarg.kankor.db.TableLocation;
import net.golbarg.kankor.db.TableUser;
import net.golbarg.kankor.model.ErrorMessage;
import net.golbarg.kankor.model.Gender;
import net.golbarg.kankor.model.Location;
import net.golbarg.kankor.model.User;
import net.golbarg.kankor.controller.FileChooseDialog;
import org.h2.util.json.JSONItemType;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserRegisterControllerView implements Initializable {
    @FXML
    private ImageView imgUserProfile;
    @FXML
    private Button btnSelectImage;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPasswordConfirm;

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
    private Text txtStatusMessage;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    private User user;
    private File selectedUserImage;
    ObservableList<Location> provinceList = FXCollections.observableArrayList();
    ObservableList<Gender> genderList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new User();
        txtStatusMessage.setText("");

        btnSelectImage.setOnAction(event -> {
            handleSelectImage(null);
        });

        provinceList.addAll(new TableLocation().getAllProvince());
        comboProvince.setItems(provinceList);

        genderList.addAll(Gender.getAll());
        comboGender.setItems(genderList);

        Callback<ListView<Location>, ListCell<Location>> cellFactory = new Callback<ListView<Location>, ListCell<Location>>() {
            @Override
            public ListCell call(ListView<Location> param) {
                final Label lblLead = new Label();
                FontIcon iconLocation = new FontIcon("fas-map-marker-alt");
                iconLocation.setIconSize(14);

                final ListCell<Location> cell = new ListCell<Location>(){
                    @Override
                    protected void updateItem(Location item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            lblLead.setText(item.getPersianName());
                            setText(item.getPersianName());
                            setGraphic(iconLocation);
                        } else {
                            lblLead.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };

                return cell;
            }
        };

        comboProvince.setButtonCell(cellFactory.call(null));
        comboProvince.setCellFactory(cellFactory);
        comboProvince.getSelectionModel().selectFirst();

        Callback<ListView<Gender>, ListCell<Gender>> cellFactoryGender = new Callback<ListView<Gender>, ListCell<Gender>>() {
            @Override
            public ListCell call(ListView<Gender> param) {
                final Label lblLead = new Label();
                FontIcon icon = new FontIcon("fab-odnoklassniki");
                icon.setIconSize(14);

                final ListCell<Gender> cell = new ListCell<Gender>(){
                    @Override
                    protected void updateItem(Gender item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            lblLead.setText(item.getLabel());
                            setText(item.getLabel());
                            setGraphic(icon);
                        } else {
                            lblLead.setText("");
                            setText("");
                            setGraphic(null);
                        }
                    }
                };

                return cell;
            }
        };

        comboGender.setButtonCell(cellFactoryGender.call(null));
        comboGender.setCellFactory(cellFactoryGender);
        comboGender.getSelectionModel().selectFirst();

        btnSave.setOnAction(event -> {
            ArrayList<ErrorMessage> errors = new ArrayList<>();
            String userImage = "";
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String passwordConfirm = txtPasswordConfirm.getText();
            String name = txtName.getText();
            String lastName = txtLastName.getText();
            String fatherName = txtFatherName.getText();
            String phoneNumber = txtPhoneNumber.getText();
            Gender selectedGender = genderList.get(comboGender.getSelectionModel().getSelectedIndex());
            Location province = provinceList.get(comboProvince.getSelectionModel().getSelectedIndex());
            String schoolName = txtSchoolName.getText();

            if(selectedUserImage != null) {
                userImage = selectedUserImage.getName();
            }

            validateInput(txtUsername, errors, 1, "یوزرنیم باید درج گردد");

            if(password.length() < 1) {
                validateInput(txtPassword, errors, 1, "پسورد باید درج گردد");
            } else if(!password.equals(passwordConfirm)) {
                errors.add(new ErrorMessage("", "پسورد ها یکسان نمیباشد"));
                txtPassword.getStyleClass().add("input-error");
                txtPasswordConfirm.getStyleClass().add("input-error");
            } else {
                 txtPassword.getStyleClass().removeIf(style -> style.equals("input-error"));
                 txtPasswordConfirm.getStyleClass().removeIf(style -> style.equals("input-error"));
            }

            validateInput(txtName, errors, 1, "نام باید درج گردد");
            validateInput(txtLastName, errors, 1, "تخلص باید درج گردد");
            validateInput(txtFatherName, errors, 1, "نام پدر باید درج گردد");
            validateInput(txtPhoneNumber, errors, 1, "شماره تلفن باید درج گردد");
            validateInput(txtSchoolName, errors, 1, "مکتب باید درج گردد");

            if(errors.size() < 1) {
                User user = new User(0, name, lastName, fatherName, username, password, province, schoolName, phoneNumber, selectedGender, userImage);
                new TableUser().create(user);

                txtStatusMessage.getStyleClass().removeIf(style -> style.equals("txt-error-message"));
                txtStatusMessage.getStyleClass().add("txt-success-message");
                txtStatusMessage.setText("معلومات موفقانه ذخیره شده.");
//                btnSave.setDisable(true);
            } else {
                txtStatusMessage.getStyleClass().removeIf(style -> style.equals("txt-success-message"));
                txtStatusMessage.getStyleClass().add("txt-error-message");
                txtStatusMessage.setText("برای ذخیره سازی معلومات را کامل درج نمائید.");
            }
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

    private void validateInput(TextField input, ArrayList<ErrorMessage> errorList, int min_length, String errorMessage) {
        if(input.getText().length() < min_length) {
            errorList.add(new ErrorMessage("", errorMessage));
            input.getStyleClass().add("input-error");
        } else {
            input.getStyleClass().removeIf(style -> style.equals("input-error"));
        }
    }

    public Button getBtnCancel() {
        return btnCancel;
    }
}
