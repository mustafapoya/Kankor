package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class KankorResultItemViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblSchoolName;
    @FXML
    private Label lblProvince;
    @FXML
    private Label lblScore;
    @FXML
    private Label lblAcceptedField;
    @FXML
    private Label lblExamDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
