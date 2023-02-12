package net.golbarg.kankor.view;

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
import net.golbarg.kankor.model.Exam;
import net.golbarg.kankor.model.ExamResult;
import net.golbarg.kankor.model.Faculty;
import net.golbarg.kankor.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ExamFormViewController implements Initializable {
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
    private BorderPane borderPaneExam;
    @FXML
    private Button btnUniversity;
    ExamViewController examViewController;

    // University tab
    @FXML
    private BorderPane borderPaneUniversity;
    @FXML
    private Button btnViewResult;
    UniversityFormViewController universityViewController;
    UniversitySelection universitySelection;
    //
    @FXML
    private BorderPane borderPaneExamResult;
    ExamResultViewController examResultViewController;
    //
    @FXML
    private BorderPane borderPaneExamReview;
    //
    User user;
    private ArrayList<Tab> tabs = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabs.addAll(Arrays.asList(tabExam, tabUniversity, tabResult, tabReview));
        enableTab(0);
        user = SystemController.currentUser;

        // init exam
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("exam-view.fxml"));
            BorderPane examView = fxmlLoader.load();
            examViewController = fxmlLoader.getController();
            borderPaneExam.setCenter(examView);
            examViewController.startExamProcess();

            btnUniversity.setOnAction(event -> {
                examViewController.processQuestionAnswers();
                enableTab(1);
                universitySelection = new UniversitySelection();
                universitySelection.run();
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

    class UniversitySelection extends Thread {
        @Override
        public void run() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("university-form-view.fxml"));
                BorderPane universityFormView = fxmlLoader.load();
                universityViewController = fxmlLoader.getController();
                borderPaneUniversity.setCenter(universityFormView);

                btnViewResult.setOnAction(event -> {
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

            ObservableList<FieldSelectionViewController> fields = universityViewController.getFields();

            ObservableList<Faculty> university = examViewController.getExamController().getUniversity(universityViewController.getFields());
            Faculty passedField = examViewController.getExamController().getPassedField(university, examViewController.getExamController().getKankorScore());

            ExamController examController = examViewController.getExamController();
            String result = passedField == null ? "بی نتیجه" : passedField.getName();

            ExamResult examResult = new ExamResult(0, examController.getKankorScore(), examController.getTotalCorrect(),
                                        examController.getQuestionGenerator().getTotalQuestion() - examController.getTotalCorrect(),
                                                    result, examViewController.getDuration());

            examResultViewController.initData(examResult);

//          examResultViewController.saveExamResult();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
