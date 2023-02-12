package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import net.golbarg.kankor.controller.SystemController;
import net.golbarg.kankor.model.User;

import java.io.IOException;
import java.net.URL;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableTabs();
        user = SystemController.currentUser;

        // init exam
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("exam-view.fxml"));
            BorderPane examView = fxmlLoader.load();
            examViewController = fxmlLoader.getController();
            borderPaneExam.setCenter(examView);
            examViewController.startExamProcess();
            examViewController.getBtnUniversity().setOnAction(event -> {
                gotoUniversityTab();
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void gotoUniversityTab() {
        tabPane.getTabs().get(0).setDisable(true);
        tabPane.getTabs().get(1).setDisable(false);
        tabPane.getSelectionModel().select(1);

        try {
            universitySelection = new UniversitySelection();
            universitySelection.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void disableTabs() {
        tabUniversity.setDisable(true);
        tabResult.setDisable(true);
        tabReview.setDisable(true);
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
                    tabPane.getTabs().get(1).setDisable(true);
                    tabPane.getTabs().get(2).setDisable(false);
                    tabPane.getSelectionModel().select(2);
                    try {
                        ExamResult result = new ExamResult(universitySelection);
                        result.run();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                });

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ExamResult extends Thread {
        UniversitySelection university;

        public ExamResult(UniversitySelection university) {
            this.university = university;
        }

        @Override
        public void run() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ExamViewController.class.getResource("exam-result-view.fxml"));
                BorderPane examResultView = fxmlLoader.load();
                examResultViewController = fxmlLoader.getController();
                borderPaneExamResult.setCenter(examResultView);
                examResultViewController.setDuration(examViewController.stopWatchWorker.messageProperty().get());

                examResultViewController.setUniversity(universityViewController, examViewController);
                examResultViewController.saveExamResult();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
