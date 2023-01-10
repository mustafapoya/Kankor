package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionReviewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private Button btnNote;
    @FXML
    private Button btnShowAnswer;

    @FXML
    private ComboBox<String> comboSubject;
    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    @FXML
    private ScrollPane scrollPaneContent;
    @FXML
    private VBox vbContentContainer;
    @FXML
    private Pagination pagination;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
