package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginViewController implements Initializable {

    @FXML
    private ImageView imgUserImage;
    @FXML
    private ComboBox comboUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(actionEvent -> {
            System.out.println("trying to login");
        });
    }
}
