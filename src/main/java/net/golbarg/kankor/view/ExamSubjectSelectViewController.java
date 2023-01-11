package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ExamSubjectSelectViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private CheckBox checkBoxType;
    @FXML
    private Spinner<String> spinnerNumberOf;
    @FXML
    private Label lblDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    

    public BorderPane getRoot() {
        return root;
    }

    public CheckBox getCheckBoxType() {
        return checkBoxType;
    }

    public Spinner<String> getSpinnerNumberOf() {
        return spinnerNumberOf;
    }

    public Label getLblDescription() {
        return lblDescription;
    }
}
