package net.golbarg.kankor.view.exam;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import net.golbarg.kankor.controller.SystemController;
import net.golbarg.kankor.controller.ui.DialogController;
import net.golbarg.kankor.controller.ui.UIController;
import net.golbarg.kankor.model.ExamResult;
import net.golbarg.kankor.model.UniversityFaculty;
import net.golbarg.kankor.model.User;
import net.golbarg.kankor.view.exam.component.FieldSelectionViewController;

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
    UniversityViewController universityViewController;
    //
    @FXML
    private BorderPane borderPaneExamResult;
    ExamResultViewController examResultViewController;
    //
    @FXML
    private BorderPane borderPaneExamReview;
    @FXML
    private Button btnBackResult;

    //
    User user;
    private ArrayList<Tab> tabs = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabs.addAll(Arrays.asList(tabExam, tabUniversity, tabResult, tabReview));
        UIController.enableTab(tabPane, tabs, 0);

        user = SystemController.currentUser;

        // init exam
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ExamFormViewController.class.getResource("exam-view.fxml"));
            BorderPane examView = fxmlLoader.load();
            examViewController = fxmlLoader.getController();
            borderPaneExam.setCenter(examView);
            examViewController.startExamProcess();

            btnUniversity.setOnAction(event -> {
                examViewController.stopExamProcess();
                examViewController.processQuestionAnswers();
                UIController.enableTab(tabPane, tabs, 1);
                loadUniversitySelectionView();
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void loadUniversitySelectionView(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ExamFormViewController.class.getResource("university-view.fxml"));
            BorderPane universityFormView = fxmlLoader.load();
            universityViewController = fxmlLoader.getController();
            borderPaneUniversity.setCenter(universityFormView);

            btnViewResult.setOnAction(event -> {
                if(universityViewController.isAnyFieldSelected()) {
                    UIController.enableTab(tabPane, tabs, 2);
                    loadExamResultView();
                } else {
                    DialogController.getAlert(Alert.AlertType.ERROR, "university Field",
                                    "Field Selection", "You should select at least one university")
                            .showAndWait();
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void loadExamResultView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ExamFormViewController.class.getResource("exam-result-view.fxml"));
            BorderPane examResultView = fxmlLoader.load();
            examResultViewController = fxmlLoader.getController();
            borderPaneExamResult.setCenter(examResultView);

            String passedField = getPassedField();

            // TODO: implement exam here
            ExamResult examResult = examViewController.getExamResult(passedField);
            examResultViewController.initData(examResult);
            examResultViewController.saveExamResult(examResult);

            examResultViewController.getBtnCheckQuestions().setOnAction(event -> {
                UIController.enableTab(tabPane, tabs, 3);
                loadExamReviewView();
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getPassedField() {
        ObservableList<FieldSelectionViewController> fields = universityViewController.getFields();
        ObservableList<UniversityFaculty> university = examViewController.getExamController().getUniversity(fields);
        UniversityFaculty passedField = examViewController.getExamController().getPassedField(university, examViewController.getExamController().getKankorScore());

        return passedField == null ? "بی نتیجه" : passedField.getName();
    }

    private void loadExamReviewView() {
        try {
            FXMLLoader reviewLoader = new FXMLLoader(ExamFormViewController.class.getResource("exam-review-view.fxml"));
            BorderPane reviewView = reviewLoader.load();
            ExamReviewViewController examReviewViewController = reviewLoader.getController();
            examReviewViewController.initData(examViewController.getQuestionList(), examViewController.getAnswerSheet());
            borderPaneExamReview.setCenter(reviewView);
            btnBackResult.setOnAction(event2-> {
                UIController.enableTab(tabPane, tabs, 2);
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
