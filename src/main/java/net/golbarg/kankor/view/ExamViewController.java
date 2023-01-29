package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.golbarg.kankor.controller.CountDownWorker;
import net.golbarg.kankor.controller.ExamController;
import net.golbarg.kankor.controller.StopWatchWorker;
import net.golbarg.kankor.model.Question;

import java.net.URL;
import java.util.ResourceBundle;

public class ExamViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabExam;
    @FXML
    private Tab tabUniversity;
    @FXML
    private Tab tabResult;
    @FXML
    private Tab tabReview;

    // Exam Tab Section
    @FXML
    private ScrollPane spAnswerSheet;
    @FXML
    private HBox hboxAnswers;
    @FXML
    private Label lblUpTimer;
    @FXML
    private Label lblDownTimer;
    @FXML
    private Label lblClock;
    @FXML
    private Label lblMathQuestion;
    @FXML
    private Label lblNaturalQuestion;
    @FXML
    private Label lblSocialQuestion;
    @FXML
    private Label lblAlsanaQuestion;
    @FXML
    private ScrollPane spQuestion;
    @FXML
    private VBox vbQuestion;
    @FXML
    private Button btnUniversity;

    // Up Timer
    static StopWatchWorker stopWatchWorker;
    Thread upTimer;

    // Down Timer
    CountDownWorker countDownWorker;
    Thread downTimer;

    ObservableList<Question> questionList = FXCollections.observableArrayList();
    ExamController examController;
    AnswerSheetViewController answerSheet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
