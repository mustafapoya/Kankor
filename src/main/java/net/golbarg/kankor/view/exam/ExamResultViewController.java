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
import net.golbarg.kankor.model.Exam;
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

    public void initData(Exam exam) {
        User user = SystemController.currentUser;
        //
        lblFullName.setText(user.getName() + ", " + user.getLastName());
        lblKankorId.setText("Kankor ID: " + user.getId());

        // TODO: implement Exam Details
        lblTime.setText(Util.convertSecondsToTimeFormat(exam.getExamDuration()));
        lblExamScore.setText(exam.getTotalScore() + "");
        lblExamResult.setText(exam.getPassedField());
        lblCorrectAnswers.setText("Total Correct Answer");
        lblWrongAnswers.setText("Total Wrong Answer");
    }

    // TODO: date parameter, user should be checked its error prone
    public void saveExamResult(Exam exam) {
        new TableExam().create(exam);
    }

    public void setUniversity(UniversityViewController universityView, ExamViewController examView) {
        ObservableList<FieldSelectionViewController> list = universityView.getFields();
        ObservableList<UniversityFaculty> universityList = examView.getExamController().getUniversity(list);

        for (int i = 0; i < universityList.size(); i++) {
            System.out.println("selected -> " + universityList.get(i).toString());
        }

        System.out.println("set university: " + examView.getExamController().getKankorScore());

        if (universityList.size() > 0) {
            UniversityFaculty universityFaculty = examView.getExamController().getPassedField(universityList, 100);
            if (universityFaculty != null) {
                setKankorResult(universityFaculty.getUniversity() + ", " + universityFaculty.getName());
            } else {
                setKankorResult("بی نتیجه !");
            }
        } else {
            setKankorResult("شما هیچ رشته ای را انتخاب نکرده اید!");
        }
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

    public Button getBtnShareResult() {
        return btnShareResult;
    }

    public void setDuration(String duration) {
        lblTime.setText(duration);
    }

    public void setKankorResult(String value) {
        lblExamResult.setText(value);
    }

    public String getKankorResult() {
        return lblExamResult.getText();
    }
    public Button getBtnShare() {
        return btnShareResult;
    }

}
