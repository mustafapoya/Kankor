package net.golbarg.kankor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import net.golbarg.kankor.controller.ExamController;
import net.golbarg.kankor.controller.SystemController;
import net.golbarg.kankor.model.ExamResult;
import net.golbarg.kankor.model.UniversityFaculty;
import net.golbarg.kankor.model.Question;
import net.golbarg.kankor.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ExamSpecificViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabSubjectSelection;
    @FXML
    private Tab tabExam;
    @FXML
    private Tab tabResult;
    @FXML
    private Tab tabReview;

    @FXML
    private BorderPane borderPaneSubjectSelection;
    @FXML
    private Button btnStartExam;
    ExamSubjectSelectViewController examSubjectSelectViewController;

    @FXML
    private BorderPane borderPaneExam;
    @FXML
    private Button btnEndExam;
    ExamViewController examViewController;
    ExamForm examForm;

    @FXML
    private BorderPane borderPaneExamResult;
    ExamResultViewController examResultViewController;

    @FXML
    private BorderPane borderPaneExamReview;
    @FXML
    private Button btnBackResult;

    User user;
    private ArrayList<Tab> tabs = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabs.addAll(Arrays.asList(tabSubjectSelection, tabExam, tabResult, tabReview));
        enableTab(0);
        user = SystemController.currentUser;

        // init exam
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ExamSpecificViewController.class.getResource("exam-subject-select-view.fxml"));
            BorderPane examView = fxmlLoader.load();
            examSubjectSelectViewController = fxmlLoader.getController();
            borderPaneSubjectSelection.setCenter(examView);

            btnStartExam.setOnAction(event -> {
                ObservableList<Question> questions = examSubjectSelectViewController.getQuestions();
                enableTab(1);
                examForm = new ExamForm(questions);
                examForm.run();
            });

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void enableTab(int index) {
        for(int i = 0; i < tabs.size(); i++) {
            if(index == i) {
                tabs.get(i).setDisable(false);
                tabPane.getSelectionModel().select(i);
            } else {
                tabs.get(i).setDisable(true);
            }
        }
    }

    class ExamForm extends Thread {
        ObservableList<Question> questions;
        public ExamForm(ObservableList<Question> questions) {
            this.questions = questions;
        }

        @Override
        public void run() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("exam-view.fxml"));
                BorderPane examView = fxmlLoader.load();
                examViewController = fxmlLoader.getController();
                borderPaneExam.setCenter(examView);
                examViewController.initQuestionsList(questions);
                examViewController.startExamProcess();

                btnEndExam.setOnAction(event -> {
                    examViewController.processQuestionAnswers();
                    enableTab(2);
                    loadExamResult();
                });

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void loadExamResult() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("exam-result-view.fxml"));
            BorderPane examResultView = fxmlLoader.load();
            examResultViewController = fxmlLoader.getController();
            borderPaneExamResult.setCenter(examResultView);

            ObservableList<FieldSelectionViewController> fields = FXCollections.observableArrayList();

            ObservableList<UniversityFaculty> university = FXCollections.observableArrayList();
            UniversityFaculty passedField = examViewController.getExamController().getPassedField(university, examViewController.getExamController().getKankorScore());

            ExamController examController = examViewController.getExamController();
            String result = passedField == null ? "بی نتیجه" : passedField.getName();

            ExamResult examResult = new ExamResult(0, examController.getKankorScore(), examController.getTotalCorrect(),
                    examController.getQuestionGenerator().getTotalQuestion() - examController.getTotalCorrect(),
                    result, examViewController.getDuration());

            examResultViewController.initData(examResult);

            examResultViewController.getBtnCheckQuestions().setOnAction(event -> {
                enableTab(3);

                try {
                    FXMLLoader reviewLoader = new FXMLLoader(ExamFormViewController.class.getResource("exam-review-view.fxml"));
                    BorderPane reviewView = reviewLoader.load();
                    ExamReviewViewController examReviewViewController = reviewLoader.getController();
                    examReviewViewController.initData(examViewController.getQuestionList(), examViewController.getAnswerSheet());
                    borderPaneExamReview.setCenter(reviewView);
                    btnBackResult.setOnAction(event2-> {
                        enableTab(2);
                    });
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
