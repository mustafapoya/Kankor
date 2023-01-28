package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import net.golbarg.kankor.model.ExamResult;
import net.golbarg.kankor.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ExamResultViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private ImageView imgUserImage;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblKankorId;
    @FXML
    private ImageView imgClock;
    @FXML
    private Label lblTime;

    //
    @FXML
    private Label lblExamScore;
    @FXML
    private Label lblExamResult;
    @FXML
    private Label lblCorrectAnswers;
    @FXML
    private Label lblWrongAnswers;
    @FXML
    private Button btnCheckQuestions;
    @FXML
    private Button btnShareResult;

    private User user;
    private ExamResult examResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadData(User user, ExamResult examResult) {
        this.user = user;
        this.examResult = examResult;

        //
        lblFullName.setText(user.getName() + ", " + user.getLastName());
        lblKankorId.setText("Kankor ID: " + user.getId());

        //
        lblTime.setText(examResult.getDuration());
        lblExamScore.setText(examResult.getScore() + "");
        lblExamResult.setText(examResult.getResult());
        lblCorrectAnswers.setText(examResult.getCorrectAnswer() + "");
        lblWrongAnswers.setText(examResult.getWrongAnswer() + "");
    }

    public Button getBtnCheckQuestions() {
        return btnCheckQuestions;
    }

    public Button getBtnShareResult() {
        return btnShareResult;
    }

}
