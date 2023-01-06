package net.golbarg.kankor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.golbarg.kankor.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private Button btnWebsite;
    @FXML
    private Button btnYoutube;
    @FXML
    private Button btnGithub;
    @FXML
    private Button btnLinkedin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnWebsite.setOnAction(event -> {
            openURL("https://golbarg.net");
        });

        btnYoutube.setOnAction(event-> {
            openURL("https://www.youtube.com/@golbargnet");
        });

        btnGithub.setOnAction(event -> {
            openURL("https://github.com/mustafapoya/NahjolFasahe");
        });

        btnLinkedin.setOnAction(event -> {
            openURL("https://www.linkedin.com/company/golbargnet/");
        });
    }

    private void openURL(String url) {
        try {
            MainApp.hostServices.showDocument(url);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
