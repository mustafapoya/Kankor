package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import net.golbarg.kankor.controller.ExamController;
import net.golbarg.kankor.model.Question;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExamReviewViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private ScrollPane spContent;
    @FXML
    private VBox vbQuestionContainer;

    ObservableList<Question> questionList = FXCollections.observableArrayList();
    AnswerSheetViewController answerSheet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        test_exam_review();
    }

    private void test_exam_review() {
        try {
            ExamController examController = new ExamController();
            examController.generateQuestion();
            ObservableList<Question> questionListTemp = FXCollections.observableArrayList();
            questionListTemp.addAll(examController.getMathList());
            questionListTemp.addAll(examController.getNaturalList());
            questionListTemp.addAll(examController.getSocialList());
            questionListTemp.addAll(examController.getAlsanaList());

            FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("answer-sheet-view.fxml"));
            VBox answerSheetView = fxmlLoader.load();
            AnswerSheetViewController tmpAnswerSheet = fxmlLoader.getController();
            tmpAnswerSheet.initAnswerSheet(160);

            for(int i = 0; i < tmpAnswerSheet.getNumberOfRows(); i++) {
                tmpAnswerSheet.getRowList().get(i).setSelectedAnswer(2);
            }

            initData(questionListTemp, tmpAnswerSheet);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void initData(ObservableList<Question> questionList, AnswerSheetViewController answerSheet) {
        this.questionList.addAll(questionList);
        this.answerSheet = answerSheet;
        initQuestions();
    }

    private void initQuestions() {
        vbQuestionContainer.getChildren().clear();

        try {
            for(int i = 0; i < questionList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("question-item-view.fxml"));
                VBox questionView = fxmlLoader.load();
                QuestionItemViewController questionController = fxmlLoader.getController();
                questionController.initData(questionList.get(i), (i+1));
                questionController.setCorrectAnswer(questionList.get(i).getCorrectChoice());
                questionController.setSelectedAnswer(answerSheet.getRowList().get(i).getSelectedAnswer());
                vbQuestionContainer.getChildren().add(questionView);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
