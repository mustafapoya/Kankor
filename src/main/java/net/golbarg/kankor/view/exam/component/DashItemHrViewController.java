package net.golbarg.kankor.view.exam.component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class DashItemHrViewController implements Initializable {
    @FXML
    private HBox root;

    @FXML
    private Label lblTitle;
    @FXML
    private Label lblValue;
    @FXML
    private FontIcon icon;

    @FXML
    private ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
