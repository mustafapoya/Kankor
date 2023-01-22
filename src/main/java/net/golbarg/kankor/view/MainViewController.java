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
import net.golbarg.kankor.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private BorderPane root;

    // header
    @FXML
    private Button btnProfile;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnResource.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("pdf-view.fxml"));
                    root.setCenter(fxmlLoader.load());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

    }
}