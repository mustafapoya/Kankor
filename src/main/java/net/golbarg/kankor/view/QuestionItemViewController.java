package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import net.golbarg.kankor.controller.ExamController;
import net.golbarg.kankor.controller.QuestionGenerator;
import net.golbarg.kankor.controller.QuestionTextController;
import net.golbarg.kankor.db.TableQuestion;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.Subject;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class QuestionItemViewController implements Initializable {
    @FXML
    private VBox root;
    @FXML
    private TextFlow tfQuestion;

    @FXML
    private GridPane gridPaneAnswer;

    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lblAnswer1;
    @FXML
    private Label lblAnswer2;
    @FXML
    private Label lblAnswer3;
    @FXML
    private Label lblAnswer4;
    private TableQuestion tableQuestion;
    private ArrayList<Label> answers = new ArrayList<>();
    private ArrayList<Label> labels  = new ArrayList<>();

    // this object is for test purpose
    Question question;

    private int correctAnswer;
    private int selectedAnswer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialization
        answers.addAll(Arrays.asList(lblAnswer1, lblAnswer2, lblAnswer3, lblAnswer4));
        labels.addAll(Arrays.asList(lbl1, lbl2, lbl3, lbl4));

        // test purpose
//        tableQuestion = new TableQuestion();
//        tmpQuestion = tableQuestion.getQuestionsOf(QuestionGenerator.MATHEMATICS, 10).get(0);
//        initData(tmpQuestion);
    }

    public void initData(Question question, int number) {
        this.question = question;

        // render question
        QuestionTextController questionText = new QuestionTextController(question.getQuestion(), number);
        tfQuestion.getChildren().add(questionText);
        tfQuestion.setTranslateY(5);

        for(int i = 0; i < answers.size(); i++) {
            QuestionTextController answerText = new QuestionTextController(question.getChoices()[i], -1);
            setAnswerValue(answers.get(i), answerText);
        }

    }

    private void setAnswerValue(Label lbl, QuestionTextController question) {
        if (question.getImages().size() > 0 && question.getLabels().size() > 0) {
            lbl.setText(question.getLabels().get(0).getText());
            lbl.setGraphic(question.getImages().get(0));

        } else if (question.getImages().size() > 0) {
            lbl.setGraphic(question.getImages().get(0));
        } else if (question.getLabels().size() > 0) {
            lbl.setText(question.getLabels().get(0).getText());
        }
    }

    public void setCorrectAnswer(int i) {
        correctAnswer = i;
        switch (i) {
            case 1:
                lbl1.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer1.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            case 2:
                lbl2.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer2.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            case 3:
                lbl3.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer3.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            case 4:
                lbl4.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer4.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            default:
                break;
        }
    }

    public void selectCorrectAnswer() {
        correctAnswer = question.getCorrectChoice();
        switch (correctAnswer) {
            case 1:
                lbl1.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer1.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            case 2:
                lbl2.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer2.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            case 3:
                lbl3.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer3.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            case 4:
                lbl4.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                lblAnswer4.setStyle("-fx-text-fill: #079f20;-fx-font-weight:bold;");
                break;
            default:
                break;
        }
    }

    public void resetCorrectAnswer() {
        correctAnswer = question.getCorrectChoice();
        switch (correctAnswer) {
            case 1:
                lbl1.setStyle(null);
                lblAnswer1.setStyle(null);
                break;
            case 2:
                lbl2.setStyle(null);
                lblAnswer2.setStyle(null);
                break;
            case 3:
                lbl3.setStyle(null);
                lblAnswer3.setStyle(null);
                break;
            case 4:
                lbl4.setStyle(null);
                lblAnswer4.setStyle(null);
                break;
            default:
                break;
        }
    }

    public void setSelectedAnswer(int i) {
        selectedAnswer = i;
        if (selectedAnswer == correctAnswer) {
            return;
        }
        switch (i) {
            case 1:
                lbl1.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                lblAnswer1.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                break;
            case 2:
                lbl2.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                lblAnswer2.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                break;
            case 3:
                lbl3.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                lblAnswer3.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                break;
            case 4:
                lbl4.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                lblAnswer4.setStyle("-fx-text-fill: red;-fx-font-weight:bold;");
                break;
            default:
                break;
        }
    }
}
