package net.golbarg.kankor.view.exam;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import net.golbarg.kankor.controller.SystemController;
import net.golbarg.kankor.controller.Util;
import net.golbarg.kankor.db.TableExam;
import net.golbarg.kankor.db.TableExamResult;
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.ExamResult;
import net.golbarg.kankor.model.UniversityFaculty;
import net.golbarg.kankor.model.User;
import net.golbarg.kankor.view.exam.component.FieldSelectionViewController;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Exam exam, ExamResult examResult) {
        User user = SystemController.currentUser;
        //
        lblFullName.setText(user.getName() + ", " + user.getLastName());
        lblKankorId.setText("Kankor ID: " + user.getId());

        // TODO: implement Exam Details
        lblTime.setText(Util.convertSecondsToTimeFormat(examResult.getExamDuration()));
        lblExamScore.setText(examResult.getCorrectAnswerCount().getScore() + "");
        lblExamResult.setText(examResult.getPassedField());
        lblCorrectAnswers.setText(String.valueOf(examResult.getCorrectAnswerCount().getTotalCorrect()));
        lblWrongAnswers.setText(String.valueOf(exam.getTotalQuestion() - examResult.getCorrectAnswerCount().getTotalCorrect()));
    }

    public void saveExamResult(Exam exam, ExamResult examResult) {
        new TableExam().create(exam);
        examResult.setExam(exam);
        new TableExamResult().create(examResult);
    }

    public void shareOnWeb() {
        btnShareResult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (examViewController.examController.getKankorScore() > 250) {
//                    share();
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    String text = "برای انتشار در ویب نمره شما باید بالای 250 باشد!";
//                    alert.setContentText(text);
//                    alert.showAndWait();
//                }
            }
        });
    }

    public void share() {

    }

    public Button getBtnCheckQuestions() {
        return btnCheckQuestions;
    }
    public Button getBtnShare() {
        return btnShareResult;
    }

}
