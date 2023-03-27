package net.golbarg.kankor.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.golbarg.kankor.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private BorderPane root;

    // header
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnSetting;

    // sidebar menu
    @FXML
    private Button btnExam;
    @FXML
    private Button btnPractice;
    @FXML
    private Button btnReview;
    @FXML
    private Button btnMatch;
    @FXML
    private Button btnResource;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnResults;
    @FXML
    private Button btnNews;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAbout;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnProfile.setOnAction(event -> {
            System.out.println("Profile");
        });

        btnSetting.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("setting-view.fxml"));
                root.setCenter(fxmlLoader.load());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnExam.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("exam/exam-dashboard-view.fxml"));
                root.setCenter(fxmlLoader.load());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnPractice.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("exam/exam-specific-view.fxml"));
                root.setCenter(fxmlLoader.load());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnReview.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("exam/question-review-view.fxml"));
                root.setCenter(fxmlLoader.load());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnHelp.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("help-view.fxml"));
                root.setCenter(fxmlLoader.load());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnResource.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("resource-view.fxml"));
                root.setCenter(fxmlLoader.load());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnNews.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("news-view.fxml"));
                root.setCenter(fxmlLoader.load());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnAbout.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("about-view.fxml"));
                BorderPane element = fxmlLoader.load();
                Scene aboutScene = new Scene(element);
                aboutScene.getStylesheets().add(MainApp.class.getResource("style.css").toExternalForm());

                Stage aboutSage = new Stage();
                aboutSage.setScene(aboutScene);

                aboutSage.initModality(Modality.WINDOW_MODAL);
                aboutSage.setAlwaysOnTop(true);
                aboutSage.requestFocus();
                aboutSage.setResizable(false);
                aboutSage.initStyle(StageStyle.DECORATED);
                aboutSage.showAndWait();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}